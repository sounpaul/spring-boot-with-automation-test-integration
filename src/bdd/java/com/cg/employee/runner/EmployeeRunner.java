package com.cg.employee.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(value = CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/bdd/resources/features/"},
        glue = {"com.cg.employee.hooks", "com.cg.employee.stepdefinition"},
        plugin = {"pretty", "json:target/site/serenity/cucumber-results.json"},
        tags = "@AddEmployee",
        monochrome = false
)
public class EmployeeRunner {
}
