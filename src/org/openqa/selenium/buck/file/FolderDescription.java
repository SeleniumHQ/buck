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

import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.CellPathResolver;
import com.facebook.buck.rules.CommonDescriptionArg;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.HasSrcs;
import com.facebook.buck.rules.SourcePathRuleFinder;
import com.facebook.buck.rules.TargetGraph;
import com.facebook.buck.util.immutables.BuckStyleImmutable;
import org.immutables.value.Value;
import java.util.Optional;

public class FolderDescription implements Description<FolderArg> {

  @Override
  public Class<FolderArg> getConstructorArgType() {
    return FolderArg.class;
  }

  @Override
  public BuildRule createBuildRule(
      TargetGraph targetGraph,
      BuildTarget buildTarget,
      ProjectFilesystem projectFilesystem,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      CellPathResolver cellRoots,
      FolderArg args) {
    return new Folder(
        buildTarget,
        projectFilesystem,
        new SourcePathRuleFinder(resolver),
        args.getOut().orElse(buildTarget.getShortName()),
        args.getSrcs());
  }

  @BuckStyleImmutable
  @Value.Immutable
  interface AbstractFolderArg extends CommonDescriptionArg, HasSrcs {
    Optional<String> getOut();
  }
}
