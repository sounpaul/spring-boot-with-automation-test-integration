package com.cg.employee.runner;

import com.cg.employee.utils.BDDUtils;
import com.cg.employee.utils.EmailUtils;
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
        String filePath = "src\\bdd\\resources\\results.csv";
        BufferedReader br = null;
        String html =
                "<table width='100%' border='1' align='center'>"
                        + "<tr align='center'>"
                        + "<td><b>Scenario Name<b></td>"
                        + "<td><b>Scenario Result<b></td>"
                        + "</tr>";
        File file = new File(filePath);
        try {
            br = new BufferedReader(new FileReader(filePath));
            String currentLine, scenarioName, scenarioResult;
            Map<String, String> resultsMap = new LinkedHashMap<String, String>();
            while ((currentLine = br.readLine()) != null) {
                scenarioName = currentLine.split(Pattern.quote(","))[0].replace("\"", "");
                scenarioResult = currentLine.split(Pattern.quote(","))[1].replace("\"", "");
                resultsMap.put(scenarioName, scenarioResult);
            }
            for (Map.Entry entry : resultsMap.entrySet()) {
                html = html + "<tr align='center'>" + "<td>" + entry.getKey() + "</td>"
                        + "<td>" + entry.getValue() + "</td>" + "</tr>";
            }
            EmailUtils.sendEmail(html);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                if (file.delete())
                    logger.info("Test result file deleted");
                else
                    logger.info("Skipped/Ignored");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
