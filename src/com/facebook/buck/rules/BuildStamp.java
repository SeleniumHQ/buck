package com.facebook.buck.rules;

public class BuildStamp {

  public final static BuildStamp STABLE = new BuildStamp("unknown", "unknown", "unknown");

  private final String username;
  private final String datestamp;
  private final String sourceRevision;

  public BuildStamp(String username, String datestamp, String sourceRevision) {
    this.username = username;
    this.datestamp = datestamp;
    this.sourceRevision = sourceRevision;
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
}
