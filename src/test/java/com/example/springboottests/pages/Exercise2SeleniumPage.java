package com.example.springboottests.pages;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("singleton")
public class Exercise2SeleniumPage extends BaseSeleniumPage {
    private static final String TEXT_INPUT = "#t14";
    private static final String BUTTON_1 = "#btnButton1";
    private static final String TRAIL = "#trail .wrap";

    public void navigateToExerciseUsingSelenium() {
        this.navigateToExerciseUsingSelenium("exercise2");
    }

    public void clickBtn1UsingSelenium() {
        this.clickByUsingSelenium(BUTTON_1);
    }

    public void fillTextInputUsingSelenium(String valueToFill) {
        this.fillByUsingSelenium(TEXT_INPUT, valueToFill);
    }

    public void assertTrailValue(String value) {
        this.assertTextByUsingSelenium(value, TRAIL);
    }
}
