package org.example.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.example.automation.utils.ReportGenerator;
import org.testng.annotations.AfterSuite;

@CucumberOptions(
        features = "src/test/resources/features",
        glue="org.example.automation.stepsdef",
        plugin = {
                "pretty",
                "summary",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @AfterSuite(alwaysRun = true)
    public void generateReports(){
        ReportGenerator.generateReport();
    }
}
