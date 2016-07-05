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

package com.facebook.buck.jvm.java;

import static com.facebook.buck.zip.ZipCompressionLevel.DEFAULT_COMPRESSION_LEVEL;

import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.model.BuildTargets;
import com.facebook.buck.rules.AbstractBuildRule;
import com.facebook.buck.rules.AddToRuleKey;
import com.facebook.buck.rules.BuildContext;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildableContext;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.SourcePathResolver;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.fs.MakeCleanDirectoryStep;
import com.facebook.buck.step.fs.MkdirStep;
import com.facebook.buck.step.fs.RmStep;
import com.facebook.buck.zip.ZipStep;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class JavaDocJar extends AbstractBuildRule implements HasMavenCoordinates {

  @AddToRuleKey
  private final ImmutableSortedSet<SourcePath> sources;
  private final Path output;
  private final Path temp;
  private final Optional<String> mavenCoords;

  public JavaDocJar(
      BuildRuleParams params,
      SourcePathResolver resolver,
      ImmutableSortedSet<SourcePath> sources,
      Optional<String> mavenCoords) {
    super(params, resolver);
    this.sources = sources;
    BuildTarget target = params.getBuildTarget();
    this.output =
        BuildTargets.getGenPath(
            getProjectFilesystem(),
            target,
            String.format("%%s%s", Javac.JAVADOC_JAR));
    this.temp = BuildTargets.getScratchPath(getProjectFilesystem(), target, "%s-javadoc");
    this.mavenCoords = mavenCoords;
  }

  @Override
  public ImmutableList<Step> getBuildSteps(
      BuildContext context, BuildableContext buildableContext) {
    ImmutableList.Builder<Step> steps = ImmutableList.builder();

    Path javadocOutput = output.getParent().resolve("javadoc");
    steps.add(new RmStep(getProjectFilesystem(), javadocOutput, /* force deletion */ true, true));
    steps.add(new RmStep(getProjectFilesystem(), output, true));
    steps.add(new MkdirStep(getProjectFilesystem(), javadocOutput));
    steps.add(new MakeCleanDirectoryStep(getProjectFilesystem(), temp));


    List<String> javaDocArgs = new LinkedList<>();
    javaDocArgs.addAll(Arrays.asList("-Xdoclint:none", "-notimestamp", "-private", "-subpackages", ".",
        "-d", javadocOutput.toString()));

    for (Path source : getResolver().filterInputsToCompareToOutput(sources)) {
      javaDocArgs.add(source.toString());
    }

    ImmutableSortedSet.Builder<Path> allJars = ImmutableSortedSet.naturalOrder();
    for (BuildRule dep : getDeps()) {
      if (dep instanceof HasClasspathEntries) {
        allJars.addAll(((HasClasspathEntries)dep).getTransitiveClasspathEntries().values());
      }
    }

    steps.add(new JavaDocStep(Joiner.on(File.pathSeparator).join(allJars.build()), javaDocArgs));

    steps.add(
        new ZipStep(
            getProjectFilesystem(),
            output,
            ImmutableSet.<Path>of(),
            /* junk paths */ false,
            DEFAULT_COMPRESSION_LEVEL,
            javadocOutput));

    buildableContext.recordArtifact(output);

    return steps.build();
  }

  @Override
  public Path getPathToOutput() {
    return output;
  }

  @Override
  public Optional<String> getMavenCoords() {
    return mavenCoords;
  }
}
