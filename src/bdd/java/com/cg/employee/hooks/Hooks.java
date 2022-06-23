package com.cg.employee.hooks;

import com.cg.employee.utils.BDDUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static Logger logger = LoggerFactory.getLogger(Hooks.class);
    EnvironmentVariables environmentVariables;

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("Start of scenario : " + scenario.getName());
        //System.setProperty("recipients", EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("recipients"));
        System.setProperty("recipients",EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("recipients"));
    }

    @After
    public void afterEachScenario(Scenario scenario) {
        BDDUtils.writeTestResult(scenario.getName(), scenario.getStatus().name());
        logger.info("End of scenario : " + scenario.getName());
    }
}
