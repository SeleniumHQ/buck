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

import com.facebook.buck.parser.NoSuchBuildTargetException;
import com.facebook.buck.rules.AbstractDescriptionArg;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.CellPathResolver;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.rules.TargetGraph;
import com.google.common.collect.ImmutableSortedSet;

import java.util.Optional;

public class MozillaExtensionDescription implements Description<MozillaExtensionDescription.Arg> {

  @Override
  public Arg createUnpopulatedConstructorArg() {
    return new Arg();
  }

  @Override
  public <A extends Arg> BuildRule createBuildRule(
      TargetGraph targetGraph,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      CellPathResolver cellRoots,
      A args) throws NoSuchBuildTargetException {
    return new Xpi(
        params,
        args.chrome,
        args.components.get(),
        args.content.get(),
        args.install,
        args.resources.get(),
        args.platforms.get());
  }

  public static class Arg extends AbstractDescriptionArg {
    public SourcePath chrome;
    public Optional<ImmutableSortedSet<SourcePath>> components;
    public Optional<ImmutableSortedSet<SourcePath>> content;
    public SourcePath install;
    public Optional<ImmutableSortedSet<SourcePath>> platforms;
    public Optional<ImmutableSortedSet<SourcePath>> resources;
  }
}
