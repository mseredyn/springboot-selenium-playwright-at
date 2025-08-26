package com.example.springboottests.pages;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("singleton")
public class Exercise1SeleniumPage extends BaseSeleniumPage {
    private static final String BUTTON_1 = "#btnButton1";
    private static final String BUTTON_2 = "#btnButton2";
    private static final String TRAIL = "#trail .wrap";

    public void navigateToExerciseUsingSelenium() {
        this.navigateToExerciseUsingSelenium("exercise1");
    }

    public void clickBtn1UsingSelenium() {
        this.clickByUsingSelenium(BUTTON_1);
    }

    public void clickBtn2UsingSelenium() {
        this.clickByUsingSelenium(BUTTON_2);
    }

    public String getTrailValueUsingSelenium() {
        return this.getValueByUsingSelenium(TRAIL);
    }

    public void assertTrailValue(String expected) {
        this.assertTextByUsingSelenium(expected, TRAIL);
    }
}
