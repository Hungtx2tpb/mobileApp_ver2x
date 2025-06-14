package bb;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        plugin = {
                "pretty",
                "json:target/serenity-reports/cucumber_report.json"
        },
        glue = {"bb.defs", ""},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@TC_2"
)
public class TestSuite {
}