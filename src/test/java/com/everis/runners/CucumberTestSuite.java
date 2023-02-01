package com.everis.runners;

import environment.SystemEnvironmentVariables;
import io.cucumber.junit.CucumberOptions;
import mainframe.com.bdd.util.UtilMainframe;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Managed;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static environment.ManageEnvironment.setEnvironment;
import static net.thucydides.core.annotations.ClearCookiesPolicy.BeforeEachTest;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.everis.stepsdefinitions"},
        tags = "@ESC1ASINDATA")


public class CucumberTestSuite {
    @Managed(uniqueSession = true, clearCookies = BeforeEachTest)
    WebDriver driver;

    @BeforeClass
    public static void beforeExecution() {
        UtilMainframe.logger(CucumberTestSuite.class).info("BEFORE >>>");
        setEnvironment(SystemEnvironmentVariables.createEnvironmentVariables());
    }

    @BeforeClass
    public static void beforeAll() {
        Logger.getLogger(CucumberTestSuite.class.getName()).info("Before all execution >>>");
    }
}