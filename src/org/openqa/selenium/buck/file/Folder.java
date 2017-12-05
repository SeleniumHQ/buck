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

package org.openqa.selenium.buck.file;


import static com.facebook.buck.util.zip.ZipCompressionLevel.DEFAULT_COMPRESSION_LEVEL;

import com.facebook.buck.event.EventDispatcher;
import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.model.BuildTargets;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.SourcePathRuleFinder;
import com.facebook.buck.rules.modern.BuildCellRelativePathFactory;
import com.facebook.buck.rules.modern.Buildable;
import com.facebook.buck.rules.modern.InputDataRetriever;
import com.facebook.buck.rules.modern.InputPath;
import com.facebook.buck.rules.modern.InputPathResolver;
import com.facebook.buck.rules.modern.ModernBuildRule;
import com.facebook.buck.rules.modern.OutputPath;
import com.facebook.buck.rules.modern.OutputPathResolver;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.fs.MakeCleanDirectoryStep;
import com.facebook.buck.zip.ZipStep;
import com.facebook.buck.zip.bundler.FileBundler;
import com.facebook.buck.zip.bundler.SrcZipAwareFileBundler;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;
import java.nio.file.Path;
import javax.annotation.Nullable;

public class Folder extends ModernBuildRule<Folder> implements Buildable {
  private final String folderName;
  private final OutputPath output;
  private final ImmutableSortedSet<InputPath> srcs;

  protected Folder(
      BuildTarget target,
      ProjectFilesystem filesystem,
      SourcePathRuleFinder ruleFinder,
      String folderName,
      ImmutableSortedSet<SourcePath> srcs) {
    super(target, filesystem, ruleFinder, Folder.class);

    this.folderName = Preconditions.checkNotNull(folderName);
    this.output = new OutputPath(String.format("%s.src.zip", target.getShortName()));
    this.srcs =
        srcs.stream()
            .map(InputPath::new)
            .collect(ImmutableSortedSet.toImmutableSortedSet(Ordering.natural()));
  }

  @Override
  public ImmutableList<Step> getBuildSteps(
      EventDispatcher eventDispatcher,
      ProjectFilesystem filesystem,
      InputPathResolver inputPathResolver,
      InputDataRetriever inputDataRetriever,
      OutputPathResolver outputPathResolver,
      BuildCellRelativePathFactory buildCellPathFactory) {
    ImmutableList.Builder<Step> steps = ImmutableList.builder();

    Path out = outputPathResolver.resolvePath(output);
    steps.addAll(MakeCleanDirectoryStep.of(buildCellPathFactory.from(out.getParent())));

    Path scratch = BuildTargets.getScratchPath(
        getProjectFilesystem(),
        getBuildTarget(),
        "%s-scratch/" + folderName);
    steps.addAll(MakeCleanDirectoryStep.of(buildCellPathFactory.from(scratch)));

    FileBundler bundler = new SrcZipAwareFileBundler(getBuildTarget());
    bundler.copy(
        getProjectFilesystem(),
        buildCellPathFactory,
        steps,
        scratch,
        srcs.stream()
            .map(InputPath::getLimitedSourcePath)
            .collect(ImmutableSortedSet.toImmutableSortedSet(Ordering.natural())),
        inputPathResolver.getLimitedSourcePathResolver());
    steps.add(
        new ZipStep(
            getProjectFilesystem(),
            out,
            ImmutableSet.of(),
            false,
            DEFAULT_COMPRESSION_LEVEL,
            scratch.getParent()));

    return steps.build();
  }

  @Nullable
  @Override
  public SourcePath getSourcePathToOutput() {
    return getSourcePath(output);
  }
}
