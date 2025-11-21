package org.example.automation.utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.Collections;

public class ReportGenerator {

    public static void generateReport(){
        File outputDir =new File("target/cucumber-reports");
        Configuration config = new Configuration(outputDir, "Title Automation Suite");
        ReportBuilder builder = new ReportBuilder(Collections.singletonList("target/cucumber-reports/cucumber.json"),config);
        builder.generateReports();
    }
}
