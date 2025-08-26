package com.example.springboottests.tests;

import com.example.springboottests.SpringboottestsApplication;
import com.example.springboottests.config.listeners.StepExecutionTimeListener;
import io.qameta.allure.testng.AllureTestNg;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

@SpringBootTest(classes = SpringboottestsApplication.class)
@Listeners({AllureTestNg.class})
@DirtiesContext
public class BaseTest extends AbstractTestNGSpringContextTests {

    @AfterMethod
    public void printMap() {
        System.out.println(StepExecutionTimeListener.getStepToTimeMap());
        System.out.println(StepExecutionTimeListener.getAverageStepDuration());
        System.out.println(StepExecutionTimeListener.getTotalElapsedPerStep());
        System.out.println(StepExecutionTimeListener.getListOfElapseds());
    }
}
