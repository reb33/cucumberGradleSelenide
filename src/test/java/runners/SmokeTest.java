package runners;

import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"html:target/cucumber-report/smoketest", "json:target/cucumber.json"},
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
}
