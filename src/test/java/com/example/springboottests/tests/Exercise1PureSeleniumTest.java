package com.example.springboottests.tests;

import com.example.springboottests.pages.Exercise1SeleniumPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise1PureSeleniumTest extends BaseTest {
    @Autowired
    private Exercise1SeleniumPage exercise1SeleniumPage;

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        exercise1SeleniumPage.navigateToExerciseUsingSelenium();
    }

    @Test
    public void threeButtons() {

        String trailValue = "";
        for (int i = 0; i < 50; i++) {
            exercise1SeleniumPage.clickBtn1UsingSelenium();
            trailValue += "b1";
            exercise1SeleniumPage.assertTrailValue(trailValue);
            exercise1SeleniumPage.clickBtn2UsingSelenium();
            trailValue += "b2";
            exercise1SeleniumPage.assertTrailValue(trailValue);
        }
    }
}
