/*
 * Copyright 2016-present Facebook, Inc.
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

package com.facebook.buck.jvm.java;

import com.facebook.buck.log.Logger;
import com.facebook.buck.step.ExecutionContext;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.StepExecutionResult;
import com.facebook.buck.util.BgProcessKiller;
import com.facebook.buck.util.ProcessExecutor;
import com.google.common.base.Joiner;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JavaDocStep implements Step {

  private List<String> args = new LinkedList<String>(){{this.add("javadoc");}};
  private String classpath;
  private Logger LOG = Logger.get(JavaDocStep.class);

  public JavaDocStep(String... args) {
    for (String arg : args) {
      this.args.add(arg);
    }
  }

  public JavaDocStep(String classpath, List<String> args) {
    this.classpath = classpath;
    this.args.addAll(args);
//    LOG.error(getDescription(null));
  }

  @Override
  public StepExecutionResult execute(ExecutionContext context) throws IOException, InterruptedException {
    ProcessBuilder processBuilder = new ProcessBuilder(this.args);
    processBuilder.environment().put("CLASSPATH", classpath);

    int exitCode = -1;
    try {
      Process p = BgProcessKiller.startProcess(processBuilder);
      ProcessExecutor.Result result = context.getProcessExecutor().execute(p);
      exitCode = result.getExitCode();
    } catch (IOException e) {
      LOG.error("Failed to execute javadoc command: %s\n\nstderr: %s", getDescription(context), context.getStdErr());
    }
    return StepExecutionResult.of(exitCode);
  }

  @Override
  public String getShortName() {
    return "javadoc";
  }

  @Override
  public String getDescription(ExecutionContext context) {
    return String.format("CLASSPATH=%s;\n%s", classpath, Joiner.on(" ").join(args));
  }
}
