package com.facebook.buck.jvm.java;

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.facebook.buck.core.model.BuildTargetFactory;
import com.facebook.buck.core.model.targetgraph.TargetGraph;
import com.facebook.buck.core.rules.ActionGraphBuilder;
import com.facebook.buck.core.rules.BuildRuleParams;
import com.facebook.buck.core.rules.SourcePathRuleFinder;
import com.facebook.buck.core.rules.resolver.impl.TestActionGraphBuilder;
import com.facebook.buck.core.rules.transformer.impl.DefaultTargetNodeToBuildRuleTransformer;
import com.facebook.buck.core.sourcepath.SourcePath;
import com.facebook.buck.core.sourcepath.resolver.SourcePathResolver;
import com.facebook.buck.core.sourcepath.resolver.impl.DefaultSourcePathResolver;
import com.facebook.buck.io.filesystem.impl.FakeProjectFilesystem;
import com.facebook.buck.testutil.TemporaryPaths;
import com.facebook.buck.testutil.ZipArchive;
import com.facebook.buck.testutil.integration.ProjectWorkspace;
import com.facebook.buck.testutil.integration.TestDataHelper;
import com.google.common.collect.ImmutableSortedSet;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Rule;
import org.junit.Test;

public class SourceJarTest {

  @Rule
  public TemporaryPaths tmp = new TemporaryPaths();

  @Test
  public void outputNameShouldIndicateThatTheOutputIsASrcJar() throws InterruptedException {
    ActionGraphBuilder resolver = new TestActionGraphBuilder(
        TargetGraph.EMPTY,
        new DefaultTargetNodeToBuildRuleTransformer());

    SourceJar rule = new SourceJar(
        BuildTargetFactory.newInstance("//example:target"),
        FakeProjectFilesystem.createJavaOnlyFilesystem(),
        new BuildRuleParams(TreeSet::new, TreeSet::new, ImmutableSortedSet.of()),
        ImmutableSortedSet.of(),
        Optional.empty(),
        Optional.empty(),
        ImmutableSortedSet.of());
    resolver.addToIndex(rule);

    SourcePath output = rule.getSourcePathToOutput();

    assertNotNull(output);
    SourcePathResolver pathResolver = DefaultSourcePathResolver.from(new SourcePathRuleFinder(resolver));
    assertThat(pathResolver.getRelativePath(output).toString(), endsWith(JavaPaths.SRC_JAR));
  }

  @Test
  public void shouldIncludeSourcesFromBuildTargetsAndPlainPaths() throws IOException {
    ProjectWorkspace workspace = TestDataHelper.createProjectWorkspaceForScenario(
        this,
        "src-jar",
        tmp);
    workspace.setUp();
    Path output = workspace.buildAndReturnOutput("//:lib#src");


    ZipArchive zip = new ZipArchive(output, /* for writing? */ false);
    Set<String> fileNames = zip.getFileNames();

    assertTrue(fileNames.contains("com/example/Direct.java"));
    assertTrue(fileNames.contains("com/example/Generated.java"));

    // output should not contain a transitive dep
    assertFalse(fileNames.contains("com/example/Transitive.java"));
  }

  @Test
  public void shouldNotIncludeNonJavaFiles() throws IOException {
    ProjectWorkspace workspace = TestDataHelper.createProjectWorkspaceForScenario(
        this,
        "src-jar",
        tmp);
    workspace.setUp();

    Path output = workspace.buildAndReturnOutput("//:lib#src");


    ZipArchive zip = new ZipArchive(output, /* for writing? */ false);
    Set<String> fileNames = zip.getFileNames();

    assertFalse(fileNames.contains("com/example/hello.txt"));
  }

  @Test
  public void shouldBuildMavenisedSourceJars() throws IOException {
    ProjectWorkspace workspace = TestDataHelper.createProjectWorkspaceForScenario(
        this,
        "src-jar",
        tmp);
    workspace.setUp();

    Path output = workspace.buildAndReturnOutput("//:lib#maven,src");


    ZipArchive zip = new ZipArchive(output, /* for writing? */ false);
    Set<String> fileNames = zip.getFileNames();

    // output should not contain any files from "//:mvn-dep"
    assertFalse(fileNames.contains("com/example/MavenSource.java"));

    // output should contain a transitive dep
    assertTrue(fileNames.contains("com/example/Transitive.java"));

    // output should contain a direct dep
    assertTrue(fileNames.contains("com/example/Direct.java"));
  }
}