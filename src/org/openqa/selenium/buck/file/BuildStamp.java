package org.openqa.selenium.buck.file;

import static com.google.common.base.StandardSystemProperty.USER_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import com.facebook.buck.io.BuildCellRelativePath;
import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.model.BuildTargets;
import com.facebook.buck.rules.AbstractBuildRule;
import com.facebook.buck.rules.AddToRuleKey;
import com.facebook.buck.rules.BuildContext;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildableContext;
import com.facebook.buck.rules.ExplicitBuildTargetSourcePath;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.step.ExecutionContext;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.StepExecutionResult;
import com.facebook.buck.step.fs.MakeCleanDirectoryStep;
import com.facebook.buck.util.Ansi;
import com.facebook.buck.util.Console;
import com.facebook.buck.util.DefaultProcessExecutor;
import com.facebook.buck.util.ProcessExecutor;
import com.facebook.buck.util.ProcessExecutorParams;
import com.facebook.buck.util.Verbosity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.SortedSet;
import javax.annotation.Nullable;

/**
 * An uncacheable rule that generates information for a build stamp.
 */
public class BuildStamp extends AbstractBuildRule {

  private final Path out;
  @AddToRuleKey
  private final Kind kind;
  @AddToRuleKey
  private final VCS vcs;

  BuildStamp(
      BuildTarget target,
      ProjectFilesystem filesystem,
      Kind kind,
      VCS vcs) {
    super(target, filesystem);

    this.kind = kind;
    this.vcs = vcs;
    this.out = BuildTargets.getGenPath(filesystem, target, "%s/build-info." + kind.extension);
  }

  @Override
  public boolean isCacheable() {
    return false;
  }

  @Override
  public SortedSet<BuildRule> getBuildDeps() {
    return ImmutableSortedSet.of();
  }

  @Override
  public ImmutableList<? extends Step> getBuildSteps(
      BuildContext context, BuildableContext buildableContext) {

    buildableContext.recordArtifact(out);

    ImmutableList.Builder<Step> steps = ImmutableList.builder();

    steps.addAll(
        MakeCleanDirectoryStep.of(BuildCellRelativePath.fromCellRelativePath(
            context.getBuildCellRootPath(),
            getProjectFilesystem(),
            out.getParent())));
    steps.add(new WriteBuildInfo());

    return steps.build();
  }

  @Nullable
  @Override
  public SourcePath getSourcePathToOutput() {
    return ExplicitBuildTargetSourcePath.of(getBuildTarget(), out);
  }

  public enum Kind {
    JSON("json") {
      @Override
      public String getFormattedData(String userName, String revision) {
        return String.format(
            "{\"build\": {\"user\": \"%s\", \"revision\": \"%s\", \"time\": \"%s\"}}",
            userName,
            revision,
            ISO_DATE_TIME.format(OffsetDateTime.now(ZoneId.of("UTC"))));
      }
    },
    MANIFEST("manifest") {
      @Override
      public String getFormattedData(String userName, String revision) {
        return String.format(
            "\nName: Build-Info\nBuild-User: %s\nBuild-Revision: %s\nBuild-Time: %s\n",
            userName,
            revision,
            ISO_DATE_TIME.format(OffsetDateTime.now(ZoneId.of("UTC"))));
      }
    },
    PROPERTIES("properties") {
      @Override
      public String getFormattedData(String userName, String revision) {
        return String.format(
            "Build-User=%s\nBuild-Revision=%s\nBuild-Time=%s\n",
            userName,
            revision,
            ISO_DATE_TIME.format(OffsetDateTime.now(ZoneId.of("UTC"))));
      }
    },
    ;

    private final String extension;

    Kind(String extension) {
      this.extension = extension;
    }

    public String getExtension() {
      return extension;
    }

    public abstract String getFormattedData(String userName, String revision);
  }

  public enum VCS {
    UNKNOWN {
      @Override
      public String getBuildVersion(ProcessExecutor executor) {
        return "unknown";
      }
    },
    GIT {
      @Override
      public String getBuildVersion(ProcessExecutor executor) throws IOException, InterruptedException {
        ProcessExecutor.Result result = executor.launchAndExecute(ProcessExecutorParams.ofCommand(
            "git",
            "log",
            "--pretty=format:'%h'",
            "-1"));

        if (result.getExitCode() == 0) {
          return result.getStdout().orElse("unknown").split("\n")[0].replace("'", "");
        }
        return "unknown";
      }
    },
    MERCURIAL {
      @Override
      public String getBuildVersion(ProcessExecutor executor) throws IOException, InterruptedException {
        ProcessExecutor.Result result = executor.launchAndExecute(ProcessExecutorParams.ofCommand(
            "hg", "identify"));

        if (result.getExitCode() == 0) {
          String line = result.getStdout().orElse("unknown").split("\n")[0];
          return line.split(" ")[0];
        }
        return "unknown";
      }
    },
    ;

    public abstract String getBuildVersion(ProcessExecutor executor) throws IOException, InterruptedException;
  }

  private class WriteBuildInfo implements Step {

    @Override
    public StepExecutionResult execute(ExecutionContext context) throws IOException, InterruptedException {
      ProcessExecutor executor = new DefaultProcessExecutor(
          new Console(
              Verbosity.SILENT,
              new PrintStream(new ByteArrayOutputStream()),
              new PrintStream(new ByteArrayOutputStream()),
              Ansi.withoutTty()));

      String value;
      try {
        value = kind.getFormattedData(USER_NAME.value(), vcs.getBuildVersion(executor));
      } catch (IOException e) {
        value = kind.getFormattedData(USER_NAME.value(), "unknown");
      }
      Files.write(out, value.getBytes(UTF_8));

      return StepExecutionResult.SUCCESS;
    }

    @Override
    public String getShortName() {
      return "build_info";
    }

    @Override
    public String getDescription(ExecutionContext context) {
      return "Writing build info";
    }
  }
}
