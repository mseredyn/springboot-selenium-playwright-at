package com.example.springboottests.tests;

import com.example.springboottests.SpringboottestsApplication;
import com.example.springboottests.config.listeners.StepExecutionTimeListener;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest(classes = SpringboottestsApplication.class)
@Listeners({AllureTestNg.class})
@DirtiesContext
@Slf4j
public class BaseTest extends AbstractTestNGSpringContextTests {

    @AfterMethod
    public void printMap() {
        log.info(StepExecutionTimeListener.getStepToTimeMap().toString());
        log.info(StepExecutionTimeListener.getAverageStepDuration().toString());
        log.info(StepExecutionTimeListener.getTotalElapsedPerStep().toString());
        log.info(StepExecutionTimeListener.getListOfElapseds().toString());
    }
}
