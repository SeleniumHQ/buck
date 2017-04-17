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
import com.facebook.buck.model.BuildTargets;
import com.facebook.buck.rules.AbstractBuildRule;
import com.facebook.buck.rules.AddToRuleKey;
import com.facebook.buck.rules.BuildContext;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildableContext;
import com.facebook.buck.rules.PathSourcePath;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.SourcePathResolver;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.fs.CopyStep;
import com.facebook.buck.step.fs.MkdirStep;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

import java.nio.file.Path;

public class Xpt extends AbstractBuildRule {

  @AddToRuleKey
  private final SourcePath fallback;

  @SuppressWarnings("PMD.UnusedPrivateField")
  @AddToRuleKey
  private final SourcePath src;
  private final Path out;

  public Xpt(
      BuildRuleParams params,
      SourcePathResolver resolver,
      SourcePath src,
      SourcePath fallback) {
    super(params);

    this.fallback = Preconditions.checkNotNull(fallback);
    this.src = Preconditions.checkNotNull(src);
    Path relativePath = resolver.getRelativePath(src);
    String name = Files.getNameWithoutExtension(relativePath.toString()) + ".xpt";
    this.out = BuildTargets.getGenPath(getProjectFilesystem(), getBuildTarget(), name);
  }

  @Override
  public ImmutableList<Step> getBuildSteps(
      BuildContext context,
      BuildableContext buildableContext) {
    ImmutableList.Builder<Step> steps = ImmutableList.builder();

    context.getEventBus().post(ConsoleEvent.warning("Defaulting to fallback for " + out));
    Path from = context.getSourcePathResolver().getAbsolutePath(fallback);

    steps.add(MkdirStep.of(getProjectFilesystem(), out.getParent()));
    steps.add(CopyStep.forFile(getProjectFilesystem(), from, out));

    buildableContext.recordArtifact(out);

    return steps.build();
  }

  @Override
  public SourcePath getSourcePathToOutput() {
    return new PathSourcePath(getProjectFilesystem(), out);
  }
}
