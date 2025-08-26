package com.example.springboottests.tests;

import com.example.springboottests.pages.Exercise2PlaywrightPage;
import com.example.springboottests.pages.Exercise2SeleniumPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class Exercise2MixedTest extends BaseTest {
    private static final int CYCLES = 100;
    @Autowired
    private Exercise2SeleniumPage seleniumPage;
    @Autowired
    private Exercise2PlaywrightPage playwrightPage;

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        seleniumPage.navigateToExerciseUsingSelenium();
    }

    @Test
    public void editBoxAssertTextFromPW() {
        String trail = "";
        for (int i = 0; i < CYCLES; i++) {
            String valueToFill = UUID.randomUUID().toString().substring(0, 2);
            seleniumPage.fillTextInputUsingSelenium(valueToFill);
            seleniumPage.clickBtn1UsingSelenium();
            trail += "t14:" + valueToFill + "b1";
            playwrightPage.assertTrailValue(trail);
        }
    }

    @Test
    public void editBoxAssertTextAndFillByFromPW() {
        String trail = "";
        for (int i = 0; i < CYCLES; i++) {
            String valueToFill = UUID.randomUUID().toString().substring(0, 2);
            playwrightPage.fillTextInputUsingPlaywright(valueToFill);
            seleniumPage.clickBtn1UsingSelenium();
            trail += "t14:" + valueToFill + "b1";
            playwrightPage.assertTrailValue(trail);
        }
    }
}
