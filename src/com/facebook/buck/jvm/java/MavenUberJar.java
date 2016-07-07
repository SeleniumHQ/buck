/*
 * Copyright 2015-present Facebook, Inc.
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

import com.facebook.buck.log.Logger;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.rules.AbstractBuildRule;
import com.facebook.buck.rules.BuildContext;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildableContext;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.SourcePathResolver;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.fs.MkdirStep;
import com.facebook.buck.util.HumanReadableException;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Suppliers;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

import org.eclipse.jetty.util.ArrayQueue;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * A {@link BuildRule} used to have the provided {@link JavaLibrary} published to a maven repository
 *
 * @see #create
 */
public class MavenUberJar extends AbstractBuildRule implements MavenPublishable {

  private final static Logger LOG = Logger.get(MavenUberJar.class);

  private final Optional<String> mavenCoords;
  private final TraversedDeps traversedDeps;

  private MavenUberJar(
      TraversedDeps traversedDeps,
      BuildRuleParams params,
      SourcePathResolver resolver,
      Optional<String> mavenCoords) {
    super(params, resolver);
    this.traversedDeps = traversedDeps;
    this.mavenCoords = mavenCoords;
  }

  private static BuildRuleParams adjustParams(BuildRuleParams params, TraversedDeps traversedDeps) {
    return params.copyWithDeps(
        Suppliers.ofInstance(
            FluentIterable.from(traversedDeps.packagedDeps)
                .toSortedSet(Ordering.<BuildRule>natural())),
        Suppliers.ofInstance(ImmutableSortedSet.<BuildRule>of()));
  }

  /**
   * Will traverse transitive dependencies of {@code rootRule}, separating those that do and don't
   * have maven coordinates. Those that do will be considered maven-external dependencies. They will
   * be returned by {@link #getMavenDeps} and will end up being specified as dependencies in
   * pom.xml. Others will be packaged in the same jar as if they are just a part of the one
   * published item.
   */
  public static MavenUberJar create(
      JavaLibrary rootRule,
      BuildRuleParams params,
      SourcePathResolver resolver,
      Optional<String> mavenCoords) {
    TraversedDeps traversedDeps = TraversedDeps.traverse(ImmutableSet.of(rootRule));
    return new MavenUberJar(
        traversedDeps,
        adjustParams(params, traversedDeps),
        resolver,
        mavenCoords);
  }

  @Override
  public ImmutableList<Step> getBuildSteps(
      BuildContext context, BuildableContext buildableContext) {
    Path pathToOutput = getPathToOutput();
    MkdirStep mkOutputDirStep = new MkdirStep(getProjectFilesystem(), pathToOutput.getParent());
    JarDirectoryStep mergeOutputsStep = new JarDirectoryStep(
        getProjectFilesystem(),
        pathToOutput,
        toOutputPaths(traversedDeps.packagedDeps),
        /* mainClass */ null,
        /* manifestFile */ null);
    return ImmutableList.of(mkOutputDirStep, mergeOutputsStep);
  }

  private static ImmutableSortedSet<Path> toOutputPaths(Iterable<? extends BuildRule> rules) {
    return FluentIterable
          .from(rules)
          .transform(
              new Function<BuildRule, Path>() {
                @Nullable
                @Override
                public Path apply(BuildRule input) {
                  Path pathToOutput = input.getPathToOutput();
                  if (pathToOutput == null) {
                    return null;
                  }
                  return input.getProjectFilesystem().resolve(pathToOutput);
                }
              })
          .filter(Predicates.notNull())
          .toSortedSet(Ordering.<Path>natural());
  }

  @Override
  public Path getPathToOutput() {
    return DefaultJavaLibrary.getOutputJarPath(getBuildTarget(), getProjectFilesystem());
  }

  @Override
  public Optional<String> getMavenCoords() {
    return mavenCoords;
  }

  @Override
  public Iterable<HasMavenCoordinates> getMavenDeps() {
    return traversedDeps.mavenDeps;
  }

  @Override
  public Iterable<BuildRule> getPackagedDependencies() {
    return traversedDeps.packagedDeps;
  }

  public static class SourceJar extends JavaSourceJar implements MavenPublishable {

    private final TraversedDeps traversedDeps;

    public SourceJar(
        BuildRuleParams params,
        SourcePathResolver resolver,
        ImmutableSortedSet<SourcePath> srcs,
        Optional<String> mavenCoords,
        TraversedDeps traversedDeps) {
      super(params, resolver, srcs, mavenCoords);
      this.traversedDeps = traversedDeps;
    }

    public static SourceJar create(
        BuildRuleParams params,
        final SourcePathResolver resolver,
        ImmutableSortedSet<SourcePath> topLevelSrcs,
        Optional<String> mavenCoords) {
      // TODO(simons): This is overly broad, since we also pull in any defs from resources.
      // Should just be deps, exported_deps, provided_deps.
      TraversedDeps traversedDeps = TraversedDeps.traverse(params.getDeps());

      params = adjustParams(params, traversedDeps);

      ImmutableSortedSet<SourcePath> sourcePaths =
          FluentIterable
              .from(traversedDeps.packagedDeps)
              .filter(HasSources.class)
              .transformAndConcat(
                  new Function<HasSources, Iterable<SourcePath>>() {
                    @Override
                    public Iterable<SourcePath> apply(HasSources input) {
                      return input.getSources();
                    }
                  })
              .append(topLevelSrcs)
              .toSortedSet(Ordering.natural());
      return new SourceJar(
          params,
          resolver,
          sourcePaths,
          mavenCoords,
          traversedDeps);
    }

    @Override
    public Iterable<HasMavenCoordinates> getMavenDeps() {
      return traversedDeps.mavenDeps;
    }

    @Override
    public Iterable<BuildRule> getPackagedDependencies() {
      return traversedDeps.packagedDeps;
    }
  }

  public static class JavadocJar extends JavaDocJar implements MavenPublishable {

    private final TraversedDeps traversedDeps;

    public JavadocJar(
        BuildRuleParams params,
        SourcePathResolver resolver,
        ImmutableSortedSet<SourcePath> srcs,
        Optional<String> mavenCoords,
        TraversedDeps traversedDeps) {
      super(params, resolver, srcs, mavenCoords);
      this.traversedDeps = traversedDeps;
    }

    public static JavadocJar create(
        BuildRuleParams params,
        final SourcePathResolver resolver,
        ImmutableSortedSet<SourcePath> topLevelSrcs,
        Optional<String> mavenCoords) {
      // We need to keep a reference to the original target, since we need the classpath from it
      // for everything to work.

      // We then need to walk the transitive deps, removing anything that belongs to another maven
      // coordinate.

      // TODO(simons): This is overly broad, since we also pull in any defs from resources.
      // Should just be deps, exported_deps, provided_deps.
      Set<JavaLibrary> allTransitiveDeps = new HashSet<>();
      Set<JavaLibrary> mavenCoordinates = new HashSet<>();
      BuildTarget parent = BuildTarget.of(params.getBuildTarget().getUnflavoredBuildTarget());
      for (BuildRule rule : params.getDeps()) {
        if (!(rule instanceof JavaLibrary)) {
          continue;
        }
        if (rule.getBuildTarget().equals(parent)) {
          continue; // Otherwise we filter ourselves out. *facepalm*
        }
        JavaLibrary javaLibrary = (JavaLibrary) rule;
        ImmutableSet<JavaLibrary> transitiveDeps = javaLibrary.getTransitiveClasspathDeps();

        allTransitiveDeps.addAll(transitiveDeps);

        mavenCoordinates.addAll(
            FluentIterable.from(transitiveDeps)
                .filter(JavaLibrary.class)
                .filter(new Predicate<JavaLibrary>() {
                  @Override
                  public boolean apply(@Nullable JavaLibrary input) {
                    return input.getMavenCoords().isPresent();
                  }
                })
                .transformAndConcat(new Function<JavaLibrary, ImmutableSet<JavaLibrary>>() {
                  @Nullable
                  @Override
                  public ImmutableSet<JavaLibrary> apply(@Nullable JavaLibrary input) {
                    return input.getTransitiveClasspathDeps();
                  }
                })
                .toSet());
      }
      Set<BuildRule> depsToIncludeInDocs = FluentIterable
          .from(Sets.difference(allTransitiveDeps, mavenCoordinates))
          .filter(BuildRule.class)
          .toSet();

//      params = params.copyWithDeps(
//          Suppliers.ofInstance(
//              FluentIterable.from(depsToIncludeInDocs)
//                  .toSortedSet(Ordering.<BuildRule>natural())),
//          Suppliers.ofInstance(ImmutableSortedSet.<BuildRule>of()));

      ImmutableSortedSet<SourcePath> sourcePaths =
          FluentIterable
              .from(depsToIncludeInDocs)
              .filter(HasSources.class)
              .transformAndConcat(
                  new Function<HasSources, Iterable<SourcePath>>() {
                    @Override
                    public Iterable<SourcePath> apply(HasSources input) {
                      return input.getSources();
                    }
                  })
              .append(topLevelSrcs)
              .toSortedSet(Ordering.natural());
      return new JavadocJar(
          params,
          resolver,
          sourcePaths,
          mavenCoords,
          new TraversedDeps(FluentIterable.from(allTransitiveDeps).filter(HasMavenCoordinates.class).toSet(), depsToIncludeInDocs));
    }

    @Override
    public Iterable<HasMavenCoordinates> getMavenDeps() {
      return traversedDeps.mavenDeps;
    }

    @Override
    public Iterable<BuildRule> getPackagedDependencies() {
      return traversedDeps.packagedDeps;
    }
  }

  private static class TraversedDeps {
    public final Iterable<HasMavenCoordinates> mavenDeps;
    public final Iterable<BuildRule> packagedDeps;

    private TraversedDeps(
        Iterable<HasMavenCoordinates> mavenDeps,
        Iterable<BuildRule> packagedDeps) {
      this.mavenDeps = mavenDeps;
      this.packagedDeps = packagedDeps;

      LOG.info("Packaged deps are: " + packagedDeps);
    }

    private static TraversedDeps traverse(ImmutableSet<? extends BuildRule> roots) {
      ImmutableSortedSet.Builder<HasMavenCoordinates> depsCollector =
          ImmutableSortedSet.naturalOrder();

      ImmutableSortedSet.Builder<JavaLibrary> candidates = ImmutableSortedSet.naturalOrder();
      for (final BuildRule root : roots) {
        if (!(root instanceof HasClasspathEntries)) {
          continue;
        }
        if (root instanceof PrebuiltJar) {
          if (!((PrebuiltJar)root).getMavenCoords().isPresent()) {
            throw new HumanReadableException("Jar dependency in maven doesn't have a maven coordinate: "
                + root.getBuildTarget());
          }
        } else {
          candidates.addAll(FluentIterable
              .from(((DefaultJavaLibrary) root).getDeclaredClasspathDeps())
              .filter(new Predicate<JavaLibrary>() {
                @Override
                public boolean apply(JavaLibrary buildRule) {
                  return !root.equals(buildRule);
                }
              }));

          Queue<JavaLibrary> dependencies = new ArrayQueue<JavaLibrary>(){{
            this.addAll(((DefaultJavaLibrary) root).getDeclaredClasspathDeps());
          }};
          while (!dependencies.isEmpty()) {
            JavaLibrary dep = dependencies.remove();
            if (!dep.getMavenCoords().isPresent()) {
              if (!(dep instanceof DefaultJavaLibrary)) {
                throw new HumanReadableException("Jar dependency in maven doesn't have a maven coordinate: "
                    + dep.getBuildTarget());
              }
              for (JavaLibrary nestedDependency : ((DefaultJavaLibrary)dep).getDeclaredClasspathDeps()) {
                candidates.add(nestedDependency);
                dependencies.add(nestedDependency);
              }
            }
          }
        }
      }
      ImmutableSortedSet.Builder<JavaLibrary> removals = ImmutableSortedSet.naturalOrder();
      for (JavaLibrary javaLibrary : candidates.build()) {
        if (HasMavenCoordinates.MAVEN_COORDS_PRESENT_PREDICATE.apply(javaLibrary)) {
          depsCollector.add(javaLibrary);
          removals.addAll(javaLibrary.getTransitiveClasspathDeps());
        }
      }

      return new TraversedDeps(
          /* mavenDeps */ depsCollector.build(),
          /* packagedDeps */ Sets.union(
          roots,
          Sets.difference(
              candidates.build(),
              removals.build())));
    }
  }
}
