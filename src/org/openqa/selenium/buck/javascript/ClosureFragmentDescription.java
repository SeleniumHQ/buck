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

package org.openqa.selenium.buck.javascript;


import static java.lang.Boolean.FALSE;

import com.facebook.buck.core.cell.resolver.CellPathResolver;
import com.facebook.buck.core.description.BuildRuleParams;
import com.facebook.buck.core.description.arg.CommonDescriptionArg;
import com.facebook.buck.core.description.arg.HasDeclaredDeps;
import com.facebook.buck.core.description.attr.ImplicitDepsInferringDescription;
import com.facebook.buck.core.model.BuildTarget;
import com.facebook.buck.core.model.targetgraph.BuildRuleCreationContextWithTargetGraph;
import com.facebook.buck.core.model.targetgraph.DescriptionWithTargetGraph;
import com.facebook.buck.core.rules.BuildRule;
import com.facebook.buck.core.rules.BuildRuleCreationContext;
import com.facebook.buck.core.rules.SourcePathRuleFinder;
import com.facebook.buck.core.sourcepath.BuildTargetSourcePath;
import com.facebook.buck.core.sourcepath.SourcePath;
import com.facebook.buck.core.util.immutables.BuckStyleImmutable;
import com.facebook.buck.util.RichStream;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.util.Optional;
import org.immutables.value.Value;

public class ClosureFragmentDescription implements
    DescriptionWithTargetGraph<JsFragmentArg>,
    ImplicitDepsInferringDescription<JsFragmentArg> {

  private final JavascriptConfig config;

  public ClosureFragmentDescription(JavascriptConfig config) {
    this.config = config;
  }

  @Override
  public Class<JsFragmentArg> getConstructorArgType() {
    return JsFragmentArg.class;
  }

  @Override
  public BuildRule createBuildRule(
      BuildRuleCreationContextWithTargetGraph context,
      BuildTarget buildTarget,
      BuildRuleParams params,
      JsFragmentArg args) {
    SourcePathRuleFinder finder = new SourcePathRuleFinder(context.getActionGraphBuilder());

    return new JsFragment(
        buildTarget,
        context.getProjectFilesystem(),
        params,
        config.getClosureCompiler(args.getCompiler(), finder),
        params.getBuildDeps(),
        args.getModule(),
        args.getFunction(),
        args.getDefines(),
        args.getPrettyPrint().orElse(FALSE));
  }

  @Override
  public void findDepsForTargetFromConstructorArgs(
      BuildTarget buildTarget,
      CellPathResolver cellRoots,
      JsFragmentArg constructorArg,
      ImmutableCollection.Builder<BuildTarget> extraDepsBuilder,
      ImmutableCollection.Builder<BuildTarget> targetGraphOnlyDepsBuilder) {
    extraDepsBuilder.addAll(
        RichStream.of(config.getClosureCompilerSourcePath(constructorArg.getCompiler()))
            .filter(BuildTargetSourcePath.class)
            .map(BuildTargetSourcePath::getTarget)
            .collect(ImmutableList.toImmutableList()));
  }

  @BuckStyleImmutable
  @Value.Immutable
  public interface AbstractJsFragmentArg extends CommonDescriptionArg, HasDeclaredDeps {
    String getFunction();
    String getModule();
    Optional<Boolean> getPrettyPrint();
    @Value.Default
    default ImmutableList<String> getDefines() {
      return ImmutableList.of();
    }
    Optional<SourcePath> getCompiler();
  }
}
