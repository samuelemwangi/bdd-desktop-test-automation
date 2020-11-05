package calculator.ui;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/ui/features",
        glue = "calculator/ui/steps",
        plugin = {
                "pretty:pretty_reports",
                "testng:target/report/testng-cucumber-reports/cucumber.json",
                "html:target/report/cucumber.html",
                "json:target/report/cucumber.json",
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {

        return super.scenarios();
    }
}
