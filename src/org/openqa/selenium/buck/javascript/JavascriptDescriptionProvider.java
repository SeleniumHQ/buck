package org.openqa.selenium.buck.javascript;

import com.facebook.buck.core.description.Description;
import com.facebook.buck.core.description.DescriptionCreationContext;
import com.facebook.buck.core.model.targetgraph.DescriptionProvider;
import com.facebook.buck.core.model.targetgraph.DescriptionWithTargetGraph;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.pf4j.Extension;

@Extension
public class JavascriptDescriptionProvider implements DescriptionProvider {

  @Override
  public Collection<DescriptionWithTargetGraph<?>> getDescriptions(
      DescriptionCreationContext context) {

    JavascriptConfig jsConfig = new JavascriptConfig(context.getBuckConfig());

    return ImmutableSet.of(
        new ClosureBinaryDescription(jsConfig),
        new ClosureFragmentDescription(jsConfig),
        new ClosureLibraryDescription());
    }
}
