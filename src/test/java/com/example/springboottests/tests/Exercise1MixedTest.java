package com.example.springboottests.tests;

import com.example.springboottests.pages.Exercise1PlaywrightPage;
import com.example.springboottests.pages.Exercise1SeleniumPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise1MixedTest extends BaseTest {

    private static final int TOTAL_CYCLES = 50;
    private static final int PLAYWRIGHT_CYCLES = 10;
    private static final int SELENIUM_CYCLES = TOTAL_CYCLES - PLAYWRIGHT_CYCLES;

    @Autowired
    private Exercise1SeleniumPage seleniumPage;
    @Autowired
    private Exercise1PlaywrightPage playwrightPage;

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        seleniumPage.navigateToExerciseUsingSelenium();
    }

    @Test
    public void threeButtons() {

        String trailValue = "";

        for (int i = 0; i < SELENIUM_CYCLES; i++) {
            seleniumPage.clickBtn1UsingSelenium();
            trailValue += "b1";
            seleniumPage.assertTrailValue(trailValue);
            seleniumPage.clickBtn2UsingSelenium();
            trailValue += "b2";
            seleniumPage.assertTrailValue(trailValue);
        }

        for (int i = 0; i < PLAYWRIGHT_CYCLES; i++) {
            playwrightPage.clickBtn1UsingPlaywright();
            trailValue += "b1";
            playwrightPage.assertTrailValue(trailValue);
            playwrightPage.clickBtn2UsingPlaywright();
            trailValue += "b2";
            playwrightPage.assertTrailValue(trailValue);
        }
    }
}
