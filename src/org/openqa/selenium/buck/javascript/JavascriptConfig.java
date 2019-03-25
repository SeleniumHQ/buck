/*
 * Copyright 2015-present Facebook, Inc.
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

import com.facebook.buck.core.config.BuckConfig;
import com.facebook.buck.core.exceptions.HumanReadableException;
import com.facebook.buck.core.model.EmptyTargetConfiguration;
import com.facebook.buck.core.rules.BuildRule;
import com.facebook.buck.core.rules.SourcePathRuleFinder;
import com.facebook.buck.core.rules.tool.BinaryBuildRule;
import com.facebook.buck.core.sourcepath.SourcePath;
import com.facebook.buck.core.toolchain.tool.Tool;
import com.facebook.buck.core.toolchain.tool.impl.HashedFileTool;
import com.google.common.base.Preconditions;
import java.util.Optional;

public class JavascriptConfig {

  private final BuckConfig delegate;

  public JavascriptConfig(BuckConfig delegate) {
    this.delegate = delegate;
  }

  public Tool getClosureCompiler(
      Optional<SourcePath> compilerPath,
      SourcePathRuleFinder finder) {
    SourcePath path = getClosureCompilerSourcePath(compilerPath);
    Optional<BuildRule> rule = finder.getRule(path);
    if (rule.isPresent()) {
      Preconditions.checkState(
          rule.get() instanceof BinaryBuildRule,
          "Closure compiler must be a binary build rule");
      return ((BinaryBuildRule) rule.get()).getExecutableCommand();
    }

    return new HashedFileTool(path);
  }

  public SourcePath getClosureCompilerSourcePath(Optional<SourcePath> compilerPath) {
    Optional<SourcePath> path = delegate.getSourcePath(
        "tools",
        "closure_compiler",
        EmptyTargetConfiguration.INSTANCE);
    if (!path.isPresent()) {
      throw new HumanReadableException("Unable to determine closure compiler to use");
    }
    return compilerPath.orElse(path.get());
  }
}

