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

import com.facebook.buck.core.build.buildable.context.BuildableContext;
import com.facebook.buck.core.build.context.BuildContext;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.rules.BuildRule;
import com.facebook.buck.core.rules.BuildRuleParams;
import com.facebook.buck.core.rules.SourcePathRuleFinder;
import com.facebook.buck.core.rules.impl.AbstractBuildRuleWithDeclaredAndExtraDeps;
import com.facebook.buck.core.sourcepath.ExplicitBuildTargetSourcePath;
import com.facebook.buck.core.sourcepath.SourcePath;
import com.facebook.buck.core.sourcepath.resolver.SourcePathResolver;
import com.facebook.buck.io.BuildCellRelativePath;
import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.facebook.buck.jvm.core.HasMavenCoordinates;
import com.facebook.buck.jvm.core.JavaLibrary;
import com.facebook.buck.jvm.java.JarShape.Summary;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.fs.MkdirStep;
import com.facebook.buck.util.RichStream;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableSortedSet.Builder;
import com.google.common.collect.Ordering;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

/**
 * A {@link BuildRule} used to have the provided {@link JavaLibrary} published to a maven repository
 *
 * @see #create
 */
public class MavenUberJar extends AbstractBuildRuleWithDeclaredAndExtraDeps
    implements MavenPublishable {

  private final Optional<String> mavenCoords;
  private final Optional<SourcePath> mavenPomTemplate;
  private final JarShape.Summary shapeSummary;

  private MavenUberJar(
      JarShape.Summary shapeSummary,
      BuildTarget buildTarget,
      ProjectFilesystem projectFilesystem,
      BuildRuleParams params,
      Optional<String> mavenCoords,
      Optional<SourcePath> mavenPomTemplate) {
    super(buildTarget, projectFilesystem, params);
    this.shapeSummary = shapeSummary;
    this.mavenCoords = mavenCoords;
    this.mavenPomTemplate = mavenPomTemplate;
  }

  private static BuildRuleParams adjustParams(
      BuildRuleParams params,
      JarShape.Summary shapeSummary,
      ImmutableSortedSet<BuildRule> extras) {
    return params
        .withDeclaredDeps(ImmutableSortedSet.copyOf(Ordering.natural(), shapeSummary.getPackagedRules()))
        .withExtraDeps(extras);
  }

  /**
   * Will traverse transitive dependencies of {@code rootRule}, separating those that do and don't
   * have maven coordinates. Those that do will be considered maven-external dependencies. They will
   * be returned by {@link #getMavenDeps} and will end up being specified as dependencies in
   * pom.xml. Others will be packaged in the same jar as if they are just a part of the one
   * published item.
   */
  public static MavenUberJar create(
      SourcePathRuleFinder resolver,
      JavaLibrary rootRule,
      BuildTarget buildTarget,
      ProjectFilesystem projectFilesystem,
      BuildRuleParams params,
      Optional<String> mavenCoords,
      Optional<SourcePath> mavenPomTemplate) {
    Summary summary = JarShape.MAVEN.gatherDeps(rootRule);

    Builder<BuildRule> templateRule = ImmutableSortedSet.naturalOrder();
    mavenPomTemplate.ifPresent(path -> resolver.getRule(path).ifPresent(templateRule::add));

    return new MavenUberJar(
        summary,
        buildTarget,
        projectFilesystem,
        adjustParams(params, summary, templateRule.build()),
        mavenCoords,
        mavenPomTemplate);
  }

  @Override
  public ImmutableList<Step> getBuildSteps(
      BuildContext context, BuildableContext buildableContext) {
    Path pathToOutput = context.getSourcePathResolver().getRelativePath(getSourcePathToOutput());
    MkdirStep mkOutputDirStep =
        MkdirStep.of(
            BuildCellRelativePath.fromCellRelativePath(
                context.getBuildCellRootPath(), getProjectFilesystem(), pathToOutput.getParent()));
    JarDirectoryStep mergeOutputsStep =
        new JarDirectoryStep(
            getProjectFilesystem(),
            JarParameters.builder()
                .setJarPath(pathToOutput)
                .setEntriesToJar(
                    toOutputPaths(context.getSourcePathResolver(), shapeSummary.getPackagedRules()))
                .setMergeManifests(true)
                .build());
    return ImmutableList.of(mkOutputDirStep, mergeOutputsStep);
  }

  private static ImmutableSortedSet<Path> toOutputPaths(
      SourcePathResolver pathResolver, Iterable<? extends BuildRule> rules) {
    return RichStream.from(rules)
        .map(BuildRule::getSourcePathToOutput)
        .filter(Objects::nonNull)
        .map(pathResolver::getAbsolutePath)
        .collect(ImmutableSortedSet.toImmutableSortedSet(Ordering.natural()));
  }

  @Override
  public SourcePath getSourcePathToOutput() {
    return ExplicitBuildTargetSourcePath.of(
        getBuildTarget(),
        AbstractCompilerOutputPaths.getOutputJarPath(getBuildTarget(), getProjectFilesystem()));
  }

  @Override
  public Optional<String> getMavenCoords() {
    return mavenCoords;
  }

  @Override
  public Optional<SourcePath> getPomTemplate() {
    return mavenPomTemplate;
  }

  @Override
  public Iterable<HasMavenCoordinates> getMavenDeps() {
    return shapeSummary.getMavenDeps();
  }

  @Override
  public Iterable<BuildRule> getPackagedDependencies() {
    return shapeSummary.getPackagedRules();
  }
}
