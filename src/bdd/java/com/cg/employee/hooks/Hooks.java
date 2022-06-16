package com.cg.employee.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;

public class Hooks {

    private static Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("Start of scenario : " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        logger.info("End of scenario : " + scenario.getName());
    }
}
