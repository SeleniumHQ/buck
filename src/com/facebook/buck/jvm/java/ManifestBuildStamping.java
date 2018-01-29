package com.facebook.buck.jvm.java;

import static com.facebook.buck.util.zip.ZipCompressionLevel.DEFAULT;
import static com.facebook.buck.util.zip.ZipCompressionLevel.NONE;
import static com.facebook.buck.util.zip.ZipOutputStreams.HandleDuplicates.APPEND_TO_ZIP;

import com.facebook.buck.io.BuildCellRelativePath;
import com.facebook.buck.io.filesystem.ProjectFilesystem;
import com.facebook.buck.rules.BuildContext;
import com.facebook.buck.rules.BuildStamp;
import com.facebook.buck.rules.SourcePath;
import com.facebook.buck.step.ExecutionContext;
import com.facebook.buck.step.Step;
import com.facebook.buck.step.StepExecutionResult;
import com.facebook.buck.step.StepExecutionResults;
import com.facebook.buck.step.fs.CopyStep;
import com.facebook.buck.step.fs.MakeCleanDirectoryStep;
import com.facebook.buck.step.fs.RmStep;
import com.facebook.buck.util.zip.CustomZipEntry;
import com.facebook.buck.util.zip.CustomZipOutputStream;
import com.facebook.buck.util.zip.ZipOutputStreams;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class ManifestBuildStamping {

  private ManifestBuildStamping() {
    // Utility classes
  }

  static ImmutableList<Step> getBuildStampingSteps(
      BuildContext buildContext,
      BuildStamp stamp,
      ProjectFilesystem filesystem,
      SourcePath jarToModify) {
    final Path outputJar = buildContext.getSourcePathResolver().getRelativePath(jarToModify);

    Builder<Step> steps = ImmutableList.builder();

    // Unzip the output, modify the manifest, rewrite the output
    Path stampRoot = outputJar.getParent().resolve("temp-stamping");
    Path stampedJar = stampRoot.resolve("stamped.jar");
    steps.addAll(MakeCleanDirectoryStep.of(BuildCellRelativePath.of(stampRoot)));

    steps.add(new Step() {
      @Override
      public StepExecutionResult execute(ExecutionContext context)
          throws IOException, InterruptedException {

        Manifest manifest = filesystem.getJarManifest(outputJar);
        if (manifest == null) {
          manifest = new Manifest();
        }

        Attributes attrs = manifest.getEntries().getOrDefault("Build-Info", new Attributes());
        attrs.putValue("Build-Revision", stamp.getSourceRevision());
        attrs.putValue("Build-Time", stamp.getDatestamp());
        attrs.putValue("Build-User", stamp.getUsername());
        manifest.getEntries().put("Build-Info", attrs);

        Path temp = stampRoot.resolve("stamped.jar");
        try (
            CustomZipOutputStream zos = ZipOutputStreams.newOutputStream(temp, APPEND_TO_ZIP);
            InputStream is = filesystem.newFileInputStream(outputJar);
            ZipInputStream zis = new ZipInputStream(is)) {
          // Write out our new manifest
          CustomZipEntry entry = new CustomZipEntry("META-INF");
          entry.setCompressionLevel(NONE.getValue());
          zos.putNextEntry(entry);
          zos.closeEntry();

          entry = new CustomZipEntry(JarFile.MANIFEST_NAME);
          entry.setCompressionLevel(DEFAULT.getValue());
          zos.putNextEntry(entry);
          manifest.write(zos);
          zos.closeEntry();

          // Now copy everything else, as is.
          for (ZipEntry toCopy = zis.getNextEntry(); toCopy != null; toCopy = zis.getNextEntry()) {
            if ("META-INF".equals(toCopy.getName()) ||
                JarFile.MANIFEST_NAME.equals(toCopy.getName())) {
              continue;
            }

            zos.putNextEntry(toCopy);
            ByteStreams.copy(zis, zos);
            zos.closeEntry();
          }
        }

        return StepExecutionResults.SUCCESS;
      }

      @Override
      public String getShortName() {
        return "stamp";
      }

      @Override
      public String getDescription(ExecutionContext context) {
        return "Add build stamp";
      }
    });

    steps.add(RmStep.of(BuildCellRelativePath.of(outputJar)));
    steps.add(CopyStep.forFile(filesystem, stampedJar, outputJar));

    return steps.build();
  }

}
