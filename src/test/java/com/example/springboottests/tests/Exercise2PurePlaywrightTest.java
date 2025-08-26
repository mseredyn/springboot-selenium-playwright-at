package com.example.springboottests.tests;

import com.example.springboottests.pages.Exercise2PlaywrightPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class Exercise2PurePlaywrightTest extends BaseTest {
    private static final int PLAYWRIGHT_CYCLES = 100;
    @Autowired
    private Exercise2PlaywrightPage playwrightPage;

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        playwrightPage.navigateToExerciseUsingPlaywright();
    }

    @Test
    public void editBox() {
        String trail = "";
        for (int i = 0; i < PLAYWRIGHT_CYCLES; i++) {
            String valueToFill = UUID.randomUUID().toString().substring(0, 2);
            playwrightPage.fillTextInputUsingPlaywright(valueToFill);
            playwrightPage.clickBtn1UsingPlaywright();
            trail += "t14:" + valueToFill + "b1";
            playwrightPage.assertTrailValue(trail);
        }
    }
}
