/*
 * Copyright 2017-present Facebook, Inc.
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

package com.facebook.buck.toolchain;

import com.facebook.buck.core.util.immutables.BuckStyleImmutable;
import org.immutables.value.Value;

/**
 * Contains basic information about a {@link Toolchain} that can be used to identify and construct
 * an instance of a particular Toolchain.
 */
@Value.Immutable(builder = false, copy = false)
@BuckStyleImmutable
public interface AbstractToolchainDescriptor<T extends Toolchain> {
  @Value.Parameter
  String getName();

  @Value.Parameter
  Class<T> getToolchainClass();

  @Value.Parameter
  Class<? extends ToolchainFactory<T>> getToolchainFactoryClass();
}