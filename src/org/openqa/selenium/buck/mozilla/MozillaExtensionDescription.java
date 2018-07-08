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

import com.facebook.buck.core.description.BuildRuleParams;
import com.facebook.buck.core.description.arg.CommonDescriptionArg;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.model.targetgraph.BuildRuleCreationContextWithTargetGraph;
import com.facebook.buck.core.model.targetgraph.DescriptionWithTargetGraph;
import com.facebook.buck.core.rules.BuildRule;
import com.facebook.buck.core.rules.SourcePathRuleFinder;
import com.facebook.buck.core.sourcepath.SourcePath;
import com.facebook.buck.core.util.immutables.BuckStyleImmutable;
import com.facebook.buck.versions.VersionPropagator;
import com.google.common.collect.ImmutableSortedSet;
import org.immutables.value.Value;

public class MozillaExtensionDescription implements
    DescriptionWithTargetGraph<MozillaExtensionArg>,
    VersionPropagator<MozillaExtensionArg> {

  @Override
  public Class<MozillaExtensionArg> getConstructorArgType() {
    return MozillaExtensionArg.class;
  }

  @Override
  public BuildRule createBuildRule(
      BuildRuleCreationContextWithTargetGraph context,
      BuildTarget buildTarget,
      BuildRuleParams params,
      MozillaExtensionArg args) {
    return new Xpi(
        buildTarget,
        context.getProjectFilesystem(),
        new SourcePathRuleFinder(context.getActionGraphBuilder()),
        args.getChrome(),
        args.getComponents(),
        args.getContent(),
        args.getInstall(),
        args.getResources(),
        args.getPlatforms());
  }

  @Override
  public boolean producesCacheableSubgraph() {
    return true;
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
