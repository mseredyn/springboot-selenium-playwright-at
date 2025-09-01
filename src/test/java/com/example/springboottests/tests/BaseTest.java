package com.example.springboottests.tests;

import com.example.springboottests.SpringboottestsApplication;
import com.example.springboottests.config.PlaywrightConfig;
import com.example.springboottests.config.SeleniumConfig;
import com.example.springboottests.config.listeners.StepExecutionTimeListener;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;

@SpringBootTest(classes = SpringboottestsApplication.class)
@Listeners({AllureTestNg.class})
@Slf4j
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SeleniumConfig seleniumConfig;
    @Autowired
    private PlaywrightConfig playwrightConfig;

    @AfterMethod
    public void printMap() {
        log.info(StepExecutionTimeListener.getStepToTimeMap().toString());
        log.info("AverageStepDuration " + StepExecutionTimeListener.getAverageStepDuration().toString());
        log.info("TotalStepDuration " + StepExecutionTimeListener.getTotalElapsedPerStep().toString());
        log.info("ListOfElapseds " + StepExecutionTimeListener.getListOfElapseds().toString());
        log.info("Time increment " + StepExecutionTimeListener.getTimeIncrements().toString());
    }
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        seleniumConfig.init();
        playwrightConfig.init();
    }

    @AfterMethod
    public void tearDown() {
        playwrightConfig.destroy();
        seleniumConfig.destroy();
    }
}
