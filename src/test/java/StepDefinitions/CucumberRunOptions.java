package StepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"src/test/resources/Features"},
        plugin = {"io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm", "pretty", "json:build/cucumber-report/report.json"},
        glue = {"StepDefinitions"})
public class CucumberRunOptions {
}