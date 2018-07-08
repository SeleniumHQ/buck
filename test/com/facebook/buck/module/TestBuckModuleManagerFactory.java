/*
 * Copyright 2018-present Facebook, Inc.
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

package com.facebook.buck.module;

import com.facebook.buck.module.impl.BuckModuleJarHashProvider;
import com.facebook.buck.module.impl.DefaultBuckModuleManager;
import org.pf4j.PluginManager;

public class TestBuckModuleManagerFactory {
  public static BuckModuleManager create(PluginManager pluginManager) {
    return new DefaultBuckModuleManager(pluginManager, new BuckModuleJarHashProvider());
  }
}
