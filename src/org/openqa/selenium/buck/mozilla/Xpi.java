/*
 * Copyright 2014-present Facebook, Inc.
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

package org.openqa.selenium.buck.mozilla;

import static com.facebook.buck.util.zip.ZipCompressionLevel.DEFAULT;

import com.facebook.buck.core.build.context.BuildContext;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.model.impl.BuildTargetPaths;
import com.facebook.buck.core.rulekey.AddToRuleKey;
import com.facebook.buck.core.rules.SourcePathRuleFinder;
import com.facebook.buck.core.sourcepath.SourcePath;
import com.facebook.buck.features.filebundler.FileBundler;
import com.facebook.buck.features.filebundler.SrcZipAwareFileBundler;
import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.facebook.buck.rules.modern.BuildCellRelativePathFactory;
import com.facebook.buck.rules.modern.Buildable;
import com.facebook.buck.rules.modern.ModernBuildRule;
import com.facebook.buck.rules.modern.OutputPath;
import com.facebook.buck.rules.modern.OutputPathResolver;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.fs.CopyStep;
import com.facebook.buck.step.fs.MakeCleanDirectoryStep;
import com.facebook.buck.step.fs.MkdirStep;
import com.facebook.buck.util.PatternsMatcher;
import com.facebook.buck.zip.ZipStep;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import java.nio.file.Path;
import javax.annotation.Nullable;

public class Xpi extends ModernBuildRule<Xpi> implements Buildable {

  @AddToRuleKey
  private final OutputPath output;
  @AddToRuleKey
  private final SourcePath chrome;
  @AddToRuleKey
  private final ImmutableSortedSet<SourcePath> components;
  @AddToRuleKey
  private final ImmutableSortedSet<SourcePath> content;
  @AddToRuleKey
  private final SourcePath install;
  @AddToRuleKey
  private final ImmutableSortedSet<SourcePath> resources;
  @AddToRuleKey
  private final ImmutableSortedSet<SourcePath> platforms;

  public Xpi(
      BuildTarget target,
      ProjectFilesystem filesystem,
      SourcePathRuleFinder ruleFinder,
      SourcePath chrome,
      ImmutableSortedSet<SourcePath> components,
      ImmutableSortedSet<SourcePath> content,
      SourcePath install,
      ImmutableSortedSet<SourcePath> resources,
      ImmutableSortedSet<SourcePath> platforms) {
    super(target, filesystem, ruleFinder, Xpi.class);

    this.chrome = chrome;
    this.components = components;
    this.content = content;
    this.install = install;
    this.resources = resources;
    this.platforms = platforms;

    this.output = new OutputPath(String.format("%s.xpi", getBuildTarget().getShortName()));
  }

  @Override
  public ImmutableList<Step> getBuildSteps(
      BuildContext buildContext,
      ProjectFilesystem filesystem,
      OutputPathResolver outputPathResolver,
      BuildCellRelativePathFactory buildCellPathFactory) {
    Path scratch = BuildTargetPaths.getScratchPath(
        getProjectFilesystem(),
        getBuildTarget(),
        "%s-xpi");

    ImmutableList.Builder<Step> steps = ImmutableList.builder();

    steps.addAll(MakeCleanDirectoryStep.of(buildCellPathFactory.from(scratch)));

    steps.add(
        CopyStep.forFile(
            getProjectFilesystem(),
            buildContext.getSourcePathResolver().getAbsolutePath(chrome),
            scratch.resolve("chrome.manifest")));
    steps.add(
        CopyStep.forFile(
            getProjectFilesystem(),
            buildContext.getSourcePathResolver().getAbsolutePath(install),
            scratch.resolve("install.rdf")));

    FileBundler bundler = new SrcZipAwareFileBundler(getBuildTarget(), PatternsMatcher.EMPTY);

    Path contentDir = scratch.resolve("content");
    steps.add(MkdirStep.of(buildCellPathFactory.from(contentDir)));
    bundler.copy(
        getProjectFilesystem(),
        buildCellPathFactory,
        steps,
        contentDir,
        content,
        buildContext.getSourcePathResolver());

    Path componentDir = scratch.resolve("components");
    steps.add(MkdirStep.of(buildCellPathFactory.from(componentDir)));
    bundler.copy(
        getProjectFilesystem(),
        buildCellPathFactory,
        steps,
        componentDir,
        components,
        buildContext.getSourcePathResolver());

    Path platformDir = scratch.resolve("platform");
    steps.add(MkdirStep.of(buildCellPathFactory.from(platformDir)));
    bundler.copy(
        getProjectFilesystem(),
        buildCellPathFactory,
        steps,
        platformDir,
        platforms,
        buildContext.getSourcePathResolver());

    Path resourceDir = scratch.resolve("resource");
    steps.add(MkdirStep.of(buildCellPathFactory.from(resourceDir)));
    bundler.copy(
        getProjectFilesystem(),
        buildCellPathFactory,
        steps,
        resourceDir,
        resources,
        buildContext.getSourcePathResolver());

    Path out = outputPathResolver.resolvePath(output);
    steps.addAll(MakeCleanDirectoryStep.of(buildCellPathFactory.from(out.getParent())));
    steps.add(
        new ZipStep(
            getProjectFilesystem(),
            out.normalize().toAbsolutePath(),
            ImmutableSet.of(),
            false,
            DEFAULT,
            scratch));

    return steps.build();
  }

  @Nullable
  @Override
  public SourcePath getSourcePathToOutput() {
    return getSourcePath(output);
  }
}
