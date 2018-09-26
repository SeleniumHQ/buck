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
import com.facebook.buck.step.fs.MakeCleanDirectoryStep;
import com.facebook.buck.util.PatternsMatcher;
import com.facebook.buck.zip.ZipStep;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import java.nio.file.Path;
import javax.annotation.Nullable;

public class Folder extends ModernBuildRule<Folder> implements Buildable {
  @AddToRuleKey
  private final String folderName;
  @AddToRuleKey
  private final OutputPath output;
  @AddToRuleKey
  private final ImmutableSortedSet<SourcePath> srcs;

  protected Folder(
      BuildTarget target,
      ProjectFilesystem filesystem,
      SourcePathRuleFinder ruleFinder,
      String folderName,
      ImmutableSortedSet<SourcePath> srcs) {
    super(target, filesystem, ruleFinder, Folder.class);

    this.folderName = Preconditions.checkNotNull(folderName);
    this.output = new OutputPath(String.format("%s.src.zip", target.getShortName()));
    this.srcs = srcs;
  }

  @Override
  public ImmutableList<Step> getBuildSteps(
      BuildContext buildContext,
      ProjectFilesystem filesystem,
      OutputPathResolver outputPathResolver,
      BuildCellRelativePathFactory buildCellPathFactory) {
    ImmutableList.Builder<Step> steps = ImmutableList.builder();

    Path out = outputPathResolver.resolvePath(output);
    steps.addAll(MakeCleanDirectoryStep.of(buildCellPathFactory.from(out.getParent())));

    Path scratch = BuildTargetPaths.getScratchPath(
        getProjectFilesystem(),
        getBuildTarget(),
        "%s-scratch/" + folderName);
    steps.addAll(MakeCleanDirectoryStep.of(buildCellPathFactory.from(scratch)));

    FileBundler bundler = new SrcZipAwareFileBundler(getBuildTarget(), PatternsMatcher.EMPTY);
    bundler.copy(
        getProjectFilesystem(),
        buildCellPathFactory,
        steps,
        scratch,
        srcs,
        buildContext.getSourcePathResolver());
    steps.add(
        new ZipStep(
            getProjectFilesystem(),
            out,
            ImmutableSet.of(),
            false,
            DEFAULT,
            scratch.getParent()));

    return steps.build();
  }

  @Nullable
  @Override
  public SourcePath getSourcePathToOutput() {
    return getSourcePath(output);
  }
}
