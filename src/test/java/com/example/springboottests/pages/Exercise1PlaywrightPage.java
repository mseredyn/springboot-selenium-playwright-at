package com.example.springboottests.pages;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("singleton")
public class Exercise1PlaywrightPage extends BasePlaywrightPage {

    private static final String BUTTON_1 = "#btnButton1";
    private static final String BUTTON_2 = "#btnButton2";
    private static final String TRAIL = "#trail .wrap";

    public void navigateToExerciseUsingPlaywright() {
        this.navigateToExerciseUsingPlaywright("exercise1");
    }

    public void clickBtn1UsingPlaywright() {
        this.clickByUsingPlaywright(BUTTON_1);
    }

    public void clickBtn2UsingPlaywright() {
        this.clickByUsingPlaywright(BUTTON_2);
    }

    public String getTrailValueUsingPlaywright() {
        return this.getValueByUsingPlaywright(TRAIL);
    }

    public void assertTrailValue(String value) {
        this.assertTextByUsingPlaywright(value, TRAIL);
    }
}
