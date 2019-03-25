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
package com.facebook.buck.event.listener.devspeed;

import com.facebook.buck.io.filesystem.ProjectFilesystem;
import java.util.Optional;
import java.util.Properties;
import org.pf4j.ExtensionPoint;

/** A plugin for collecting developer speed telemetry. */
public interface DevspeedTelemetryPlugin extends ExtensionPoint {

  /**
   * If the host system is configured for it, returns a factory that the daemon can use to
   * instantiate build listeners for each build that it does.
   */
  Optional<DevspeedBuildListenerFactory> newBuildListenerFactoryForDaemon(
      ProjectFilesystem filesystem, Properties properties);
}