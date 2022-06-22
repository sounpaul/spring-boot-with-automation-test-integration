package com.cg.employee.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RunWith(value = CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/bdd/resources/features/"},
        glue = {"com.cg.employee.hooks", "com.cg.employee.stepdefinition"},
        plugin = {"pretty", "json:target/site/serenity/cucumber-results.json"},
        tags = "@EmployeeServiceTests",
        monochrome = false
)
public class EmployeeRunner {

    private static Logger logger = LoggerFactory.getLogger(EmployeeRunner.class);

    @BeforeClass
    public static void setup() {
        logger.info("-------Start of Serenity Tests-------");
    }

    @AfterClass
    public static void tearDown() {
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\target\\site\\serenity\\results.csv"))) {
            String currentLine;
            Map<String, String> resultsMap = new LinkedHashMap<String, String>();
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.contains("Story")) {
                    resultsMap.put(currentLine.split(Pattern.quote(","))[1], currentLine.split(Pattern.quote(","))[2]);
                }
            }
            resultsMap.forEach((featureName, featureResult) -> {
                logger.info(featureName + " --> " + featureResult);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
