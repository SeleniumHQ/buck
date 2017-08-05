package org.openqa.selenium.buck.file;

import com.facebook.buck.io.ProjectFilesystem;
import com.facebook.buck.model.BuildTarget;
import com.facebook.buck.parser.NoSuchBuildTargetException;
import com.facebook.buck.rules.BuildRule;
import com.facebook.buck.rules.BuildRuleParams;
import com.facebook.buck.rules.BuildRuleResolver;
import com.facebook.buck.rules.CellPathResolver;
import com.facebook.buck.rules.CommonDescriptionArg;
import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.TargetGraph;
import com.facebook.buck.util.immutables.BuckStyleImmutable;
import org.immutables.value.Value;

public class BuildStampDescription implements Description<BuildStampArg> {

  @Override
  public Class<BuildStampArg> getConstructorArgType() {
    return BuildStampArg.class;
  }

  @Override
  public BuildRule createBuildRule(
      TargetGraph targetGraph,
      BuildTarget buildTarget,
      ProjectFilesystem projectFilesystem,
      BuildRuleParams params,
      BuildRuleResolver resolver,
      CellPathResolver cellRoots,
      BuildStampArg args) throws NoSuchBuildTargetException {
    return new BuildStamp(
        buildTarget,
        projectFilesystem,
        args.getKind(),
        args.getSourceControlSystem());
  }

  @BuckStyleImmutable
  @Value.Immutable
  interface AbstractBuildStampArg extends CommonDescriptionArg {
    @Value.Default
    default BuildStamp.Kind getKind() {
      return BuildStamp.Kind.JSON;
    }

    @Value.Default
    default BuildStamp.VCS getSourceControlSystem() {
      return BuildStamp.VCS.UNKNOWN;
    }
  }

}
