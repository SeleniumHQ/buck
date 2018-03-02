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

import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleCreationContext;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.CommonDescriptionArg;
import com.facebook.buck.rules.DefaultSourcePathResolver;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.SourcePathRuleFinder;
import com.facebook.buck.util.immutables.BuckStyleImmutable;
import com.google.common.io.Files;
import java.nio.file.Path;
import org.immutables.value.Value;

public class MozillaXptDescription implements Description<MozillaXptArg> {

  @Override
  public Class<MozillaXptArg> getConstructorArgType() {
    return MozillaXptArg.class;
  }

  @Override
  public BuildRule createBuildRule(
      BuildRuleCreationContext context,
      BuildTarget buildTarget,
      BuildRuleParams params,
      MozillaXptArg args) {
    Path sourcePath = DefaultSourcePathResolver.from(
        new SourcePathRuleFinder(context.getBuildRuleResolver()))
        .getRelativePath(args.getSrc());
    String name = Files.getNameWithoutExtension(sourcePath.toString()) + ".xpt";

    return new Xpt(
        buildTarget,
        context.getProjectFilesystem(),
        params,
        name,
        args.getSrc(),
        args.getFallback());
  }

  @BuckStyleImmutable
  @Value.Immutable
  interface AbstractMozillaXptArg extends CommonDescriptionArg {
    SourcePath getFallback();
    SourcePath getSrc();
  }
}
