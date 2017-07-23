package runners;

import com.codeborne.selenide.Configuration;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"html:build/cucumber-report/smoketest", "json:build/cucumber.json"},
        features = "src/test/java/features",
        glue = "steps")

public class SmokeTest
{

    @BeforeClass
    static public void setupTimeout()
    {
        Configuration.timeout = 10000;
        System.setProperty("webdriver.chrome.driver", "C:/drv/chromedriver.exe");
        Configuration.browser = "chrome";

    }

    @AfterClass
    static public void gen() throws Exception {
        CucumberResultsOverview results = new CucumberResultsOverview();
        results.setOutputDirectory("target");
        results.setOutputName("cucumber-results");
        results.setSourceFile("./target/cucumber.json");
        results.executeFeaturesOverviewReport();
    }
}
