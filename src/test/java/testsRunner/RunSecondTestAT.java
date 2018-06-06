package testsRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import util.WebConnector;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-html-report/2.json"},
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        tags = {"@findaretailerdemo","~@ignore"},
        monochrome = true
)

public class RunSecondTestAT extends WebConnector {
    @AfterClass
    public static void closeBrowser() {
        getDriver().quit();
    }
}
