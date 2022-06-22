package com.cg.employee.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("Start of scenario : " + scenario.getName());
    }

    @After
    public void afterEachScenario(Scenario scenario) {
        logger.info("End of scenario : " + scenario.getName());
    }
}
