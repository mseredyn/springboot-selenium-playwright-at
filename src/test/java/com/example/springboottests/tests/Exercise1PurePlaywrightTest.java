package com.example.springboottests.tests;

import com.example.springboottests.pages.Exercise1PlaywrightPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise1PurePlaywrightTest extends BaseTest {
    @Autowired
    private Exercise1PlaywrightPage exercise1PlaywrightPage;

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        exercise1PlaywrightPage.navigateToExerciseUsingPlaywright();
    }

    @Test
    public void threeButtons() {

        String trailValue = "";
        for (int i = 0; i < 50; i++) {
            exercise1PlaywrightPage.clickBtn1UsingPlaywright();
            trailValue += "b1";
            exercise1PlaywrightPage.assertTrailValue(trailValue);
            exercise1PlaywrightPage.clickBtn2UsingPlaywright();
            trailValue += "b2";
            exercise1PlaywrightPage.assertTrailValue(trailValue);
        }
    }

}
