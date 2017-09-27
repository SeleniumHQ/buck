/*
 * Copyright 2013-present Facebook, Inc.
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

import com.facebook.buck.event.ConsoleEvent;
import com.facebook.buck.io.BuildCellRelativePath;
import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.model.BuildTargets;
import com.facebook.buck.rules.AbstractBuildRuleWithDeclaredAndExtraDeps;
import com.facebook.buck.rules.AddToRuleKey;
import com.facebook.buck.rules.BuildContext;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildableContext;
import com.facebook.buck.rules.PathSourcePath;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.fs.CopyStep;
import com.facebook.buck.step.fs.MkdirStep;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.nio.file.Path;

public class Xpt extends AbstractBuildRuleWithDeclaredAndExtraDeps {

  @AddToRuleKey
  private final SourcePath fallback;

  @SuppressWarnings("PMD.UnusedPrivateField")
  @AddToRuleKey
  private final SourcePath src;
  private final Path out;

  Xpt(
      BuildTarget target,
      ProjectFilesystem filesystem,
      BuildRuleParams params,
      String name,
      SourcePath src,
      SourcePath fallback) {
    super(target, filesystem, params);

    this.fallback = Preconditions.checkNotNull(fallback);
    this.src = Preconditions.checkNotNull(src);
    this.out = BuildTargets.getGenPath(getProjectFilesystem(), getBuildTarget(), name);
  }

  @Override
  public ImmutableList<Step> getBuildSteps(
      BuildContext context,
      BuildableContext buildableContext) {
    ImmutableList.Builder<Step> steps = ImmutableList.builder();

    context.getEventBus().post(ConsoleEvent.warning("Defaulting to fallback for " + out));
    Path from = context.getSourcePathResolver().getAbsolutePath(fallback);

    steps.add(MkdirStep.of(
        BuildCellRelativePath.fromCellRelativePath(
            context.getBuildCellRootPath(),
            getProjectFilesystem(),
            out.getParent())));
    steps.add(CopyStep.forFile(getProjectFilesystem(), from, out));

    buildableContext.recordArtifact(out);

    return steps.build();
  }

  @Override
  public SourcePath getSourcePathToOutput() {
    return new PathSourcePath(getProjectFilesystem(), out);
  }
}
