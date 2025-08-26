package com.example.springboottests.tests;

import com.example.springboottests.pages.Exercise2SeleniumPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class Exercise2PureSeleniumTest extends BaseTest {
    private static final int SELENIUM_CYCLES = 100;
    @Autowired
    private Exercise2SeleniumPage seleniumPage;

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        seleniumPage.navigateToExerciseUsingSelenium();
    }

    @Test
    public void editBox() {
        String trail = "";
        for (int i = 0; i < SELENIUM_CYCLES; i++) {
            String valueToFill = UUID.randomUUID().toString().substring(0, 2);
            seleniumPage.fillTextInputUsingSelenium(valueToFill);
            seleniumPage.clickBtn1UsingSelenium();
            trail += "t14:" + valueToFill + "b1";
            seleniumPage.assertTrailValue(trail);
        }
    }
}
