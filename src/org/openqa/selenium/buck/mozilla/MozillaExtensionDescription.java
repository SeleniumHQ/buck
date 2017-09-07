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

import com.facebook.buck.io.ProjectFilesystem;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.CellPathResolver;
import com.facebook.buck.rules.CommonDescriptionArg;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.TargetGraph;
import com.facebook.buck.util.immutables.BuckStyleImmutable;
import com.google.common.collect.ImmutableSortedSet;
import org.immutables.value.Value;

public class MozillaExtensionDescription implements Description<MozillaExtensionArg> {

  @Override
  public Class<MozillaExtensionArg> getConstructorArgType() {
    return MozillaExtensionArg.class;
  }

  @Override
  public BuildRule createBuildRule(
      TargetGraph targetGraph,
      BuildTarget buildTarget,
      ProjectFilesystem projectFilesystem,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      CellPathResolver cellRoots,
      MozillaExtensionArg args) {
    return new Xpi(
        buildTarget,
        projectFilesystem,
        params,
        args.getChrome(),
        args.getComponents(),
        args.getContent(),
        args.getInstall(),
        args.getResources(),
        args.getPlatforms());
  }

  @BuckStyleImmutable
  @Value.Immutable
  interface AbstractMozillaExtensionArg extends CommonDescriptionArg {
    SourcePath getChrome();
    @Value.NaturalOrder ImmutableSortedSet<SourcePath> getComponents();
    @Value.NaturalOrder ImmutableSortedSet<SourcePath> getContent();
    SourcePath getInstall();
    @Value.NaturalOrder ImmutableSortedSet<SourcePath> getPlatforms();
    @Value.NaturalOrder ImmutableSortedSet<SourcePath> getResources();
  }
}
