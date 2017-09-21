/*
 * Copyright 2016-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.jvm.kotlin;

import com.facebook.buck.io.ProjectFilesystem;
import com.facebook.buck.jvm.java.DefaultJavaLibrary;
import com.facebook.buck.jvm.java.DefaultJavaLibraryBuilder;
import com.facebook.buck.jvm.java.HasJavaAbi;
import com.facebook.buck.jvm.java.HasSources;
import com.facebook.buck.jvm.java.JarShape;
import com.facebook.buck.jvm.java.JavaBuckConfig;
import com.facebook.buck.jvm.java.JavaLibrary;
import com.facebook.buck.jvm.java.JavaLibraryDescription;
import com.facebook.buck.jvm.java.JavacOptions;
import com.facebook.buck.jvm.java.JavacOptionsFactory;
import com.facebook.buck.jvm.java.MavenUberJar;
import com.facebook.buck.jvm.java.SourceJar;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.model.Flavor;
import com.facebook.buck.model.Flavored;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.CellPathResolver;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.TargetGraph;
import com.facebook.buck.util.MoreCollectors;
import com.facebook.buck.util.immutables.BuckStyleImmutable;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import org.immutables.value.Value;
import java.util.Collection;


public class KotlinLibraryDescription
    implements Description<KotlinLibraryDescriptionArg>, Flavored {

  private final KotlinBuckConfig kotlinBuckConfig;
  private final JavaBuckConfig javaBuckConfig;
  private final JavacOptions defaultOptions;

  public static final ImmutableSet<Flavor> SUPPORTED_FLAVORS =
      ImmutableSet.of(JavaLibrary.SRC_JAR, JavaLibrary.MAVEN_JAR);

  public KotlinLibraryDescription(
      KotlinBuckConfig kotlinBuckConfig,
      JavaBuckConfig javaBuckConfig,
      JavacOptions defaultOptions) {
    this.kotlinBuckConfig = kotlinBuckConfig;
    this.javaBuckConfig = javaBuckConfig;
    this.defaultOptions = defaultOptions;
  }

  @Override
  public boolean hasFlavors(ImmutableSet<Flavor> flavors) {
    return SUPPORTED_FLAVORS.containsAll(flavors);
  }

  @Override
  public Class<KotlinLibraryDescriptionArg> getConstructorArgType() {
    return KotlinLibraryDescriptionArg.class;
  }

  @Override
  public BuildRule createBuildRule(
      TargetGraph targetGraph,
      BuildTarget buildTarget,
      ProjectFilesystem projectFilesystem,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      CellPathResolver cellRoots,
      KotlinLibraryDescriptionArg args) {

    ImmutableSortedSet<Flavor> flavors = buildTarget.getFlavors();

    BuildTarget buildTargetWithMavenFlavor = null;
    BuildRuleParams paramsWithMavenFlavor = null;
    if (flavors.contains(JavaLibrary.MAVEN_JAR)) {
      buildTargetWithMavenFlavor = buildTarget;
      paramsWithMavenFlavor = params;

      // Maven rules will depend upon their vanilla versions, so the latter have to be constructed
      // without the maven flavor to prevent output-path conflict
      buildTarget = buildTarget.withoutFlavors(JavaLibrary.MAVEN_JAR);
    }

    if (flavors.contains(JavaLibrary.SRC_JAR)) {
      JarShape shape = flavors.contains(JavaLibrary.MAVEN_JAR) ? JarShape.MAVEN : JarShape.SINGLE;

      BuildTarget unflavored = BuildTarget.of(buildTarget.getUnflavoredBuildTarget());
      BuildRule baseLibrary = resolver.requireRule(unflavored);
      JarShape.Summary summary = shape.gatherDeps(baseLibrary);

      return new SourceJar(
          buildTarget,
          projectFilesystem,
          params,
          /* passed in, but ignored */ "8",
          summary.getPackagedRules().stream()
              .filter(HasSources.class::isInstance)
              .map(rule -> ((HasSources) rule).getSources())
              .flatMap(Collection::stream)
              .collect(MoreCollectors.toImmutableSet()),
          args.getMavenCoords(),
          args.getMavenPomTemplate(),
          summary.getMavenDeps());
    }
    JavacOptions javacOptions =
        JavacOptionsFactory.create(defaultOptions, buildTarget, projectFilesystem, resolver, args);

    DefaultJavaLibraryBuilder defaultKotlinLibraryBuilder =
        new KotlinLibraryBuilder(
                targetGraph,
                buildTarget,
                projectFilesystem,
                params,
                resolver,
                cellRoots,
                kotlinBuckConfig,
                javaBuckConfig)
            .setJavacOptions(javacOptions)
            .setArgs(args);

    // We know that the flavour we're being asked to create is valid, since the check is done when
    // creating the action graph from the target graph.
    if (HasJavaAbi.isAbiTarget(buildTarget)) {
      return defaultKotlinLibraryBuilder.buildAbi();
    }

    DefaultJavaLibrary defaultKotlinLibrary = defaultKotlinLibraryBuilder.build();

    if (!flavors.contains(JavaLibrary.MAVEN_JAR)) {
      return defaultKotlinLibrary;
    } else {
      resolver.addToIndex(defaultKotlinLibrary);
      return MavenUberJar.create(
          defaultKotlinLibrary,
          buildTargetWithMavenFlavor,
          projectFilesystem,
          Preconditions.checkNotNull(paramsWithMavenFlavor),
          args.getMavenCoords(),
          args.getMavenPomTemplate());
    }
  }

  public interface CoreArg extends JavaLibraryDescription.CoreArg {
    ImmutableList<String> getExtraKotlincArguments();
  }

  @BuckStyleImmutable
  @Value.Immutable
  interface AbstractKotlinLibraryDescriptionArg extends CoreArg {}
}
