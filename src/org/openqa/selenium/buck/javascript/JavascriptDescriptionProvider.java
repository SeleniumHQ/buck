package org.openqa.selenium.buck.javascript;

import com.facebook.buck.rules.Description;
import com.facebook.buck.rules.DescriptionCreationContext;
import com.facebook.buck.rules.DescriptionProvider;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.pf4j.Extension;

@Extension
public class JavascriptDescriptionProvider implements DescriptionProvider {

    @Override
    public Collection<Description<?>> getDescriptions(DescriptionCreationContext context) {

      JavascriptConfig jsConfig = new JavascriptConfig(context.getBuckConfig());

      return Stream.of(
          new ClosureBinaryDescription(jsConfig),
          new ClosureFragmentDescription(jsConfig),
          new ClosureLibraryDescription())
          .collect(Collectors.toCollection(HashSet::new));
    }
}
