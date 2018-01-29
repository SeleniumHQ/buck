package com.facebook.buck.rules;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

import com.facebook.buck.util.Ansi;
import com.facebook.buck.util.CapturingPrintStream;
import com.facebook.buck.util.Console;
import com.facebook.buck.util.DefaultProcessExecutor;
import com.facebook.buck.util.ProcessExecutor;
import com.facebook.buck.util.ProcessExecutorParams;
import com.facebook.buck.util.Verbosity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

public class BuildStamp {

  private final String username;
  private final String datestamp;
  private final String sourceRevision;

  private BuildStamp(String username, String datestamp, String sourceRevision) {
    this.username = Objects.requireNonNull(username, "User name must be set");
    this.datestamp = Objects.requireNonNull(datestamp, "Date stamp must be set");
    this.sourceRevision = Objects.requireNonNull(sourceRevision, "Source revision must be set");
  }

  public String getUsername() {
    return username;
  }

  public String getDatestamp() {
    return datestamp;
  }

  public String getSourceRevision() {
    return sourceRevision;
  }

  @Override
  public String toString() {
    return "BuildStamp{" +
        "username='" + username + '\'' +
        ", datestamp='" + datestamp + '\'' +
        ", sourceRevision='" + sourceRevision + '\'' +
        '}';
  }

  public enum STAMP_KIND {
    NONE {
      @Override
      public Optional<BuildStamp> getBuildStamp() {
        return Optional.empty();
      }
    },
    STABLE {
      @Override
      public Optional<BuildStamp> getBuildStamp() {
        return Optional.of(new BuildStamp("unknown", "unknown", "unknown"));
      }
    },
    DETECT {
      @Override
      public Optional<BuildStamp> getBuildStamp()
          throws IOException, InterruptedException {
        // Walk up the tree until we find either a .git or a .hg directory. This is prone to
        // failure,
        // but good enough to get us up and running.
        Path current = Paths.get(".");

        VCS vcs = null;
        while (vcs == null && current != null) {
          for (VCS toCheck : VCS.values()) {
            if (toCheck.isCloneRoot(current)) {
              vcs = toCheck;
              break;
            }
          }
          current = current.getParent();
        }
        if (vcs == null) {
          vcs = VCS.UNKNOWN;
        }

        // The default console used by buck has ANSI highlighting enabled. We don't want ansi
        // strings in our output, so create our own processexecutor here.
        Console console = new Console(Verbosity.STANDARD_INFORMATION,
            new CapturingPrintStream(),
            new CapturingPrintStream(),
            Ansi.withoutTty());
        ProcessExecutor executor = new DefaultProcessExecutor(console);

        return Optional.of(
            new BuildStamp(
                System.getProperty("user.name", "unknown"),
                ISO_DATE_TIME.format(OffsetDateTime.now(ZoneId.of("UTC"))),
                vcs.getBuildVersion(executor)));
      }
    };

    public abstract Optional<BuildStamp> getBuildStamp()
        throws IOException, InterruptedException;
  }


  public enum VCS {
    UNKNOWN {
      @Override
      public boolean isCloneRoot(Path path) {
        return false;
      }

      @Override
      public String getBuildVersion(ProcessExecutor executor) {
        return "unknown";
      }
    },
    GIT {
      @Override
      public boolean isCloneRoot(Path path) {
        return Files.isDirectory(path.resolve(".git"));
      }

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
      public boolean isCloneRoot(Path path) {
        return Files.isDirectory(path.resolve(".hg"));
      }

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

    public abstract boolean isCloneRoot(Path path);
    public abstract String getBuildVersion(ProcessExecutor executor) throws IOException, InterruptedException;
  }
}
