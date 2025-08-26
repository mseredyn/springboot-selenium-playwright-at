package com.example.springboottests.pages;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("singleton")
public class Exercise2PlaywrightPage extends BasePlaywrightPage {
    private static final String TEXT_INPUT = "#t14";
    private static final String BUTTON_1 = "#btnButton1";
    private static final String TRAIL = "#trail .wrap";

    public void navigateToExerciseUsingPlaywright() {
        this.navigateToExerciseUsingPlaywright("exercise2");
    }

    public void clickBtn1UsingPlaywright() {
        this.clickByUsingPlaywright(BUTTON_1);
    }

    public void fillTextInputUsingPlaywright(String valueToFill) {
        this.fillByUsingPlaywright(TEXT_INPUT, valueToFill);
    }

    public void assertTrailValue(String value) {
        this.assertTextByUsingPlaywright(value, TRAIL);
    }


}
