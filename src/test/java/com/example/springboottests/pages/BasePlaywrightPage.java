package com.example.springboottests.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Lazy
@Component
@Scope("singleton")
public class BasePlaywrightPage {
    @Value("${base.url}")
    private String url;
    @Value("${test.seed}")
    private String testSeed;
    @Autowired
    private Page page;

    @Step
    protected Locator getByUsingPlaywright(String selector) {
        Locator locator = page.locator(selector);
        locator.scrollIntoViewIfNeeded();
        return locator;
    }

    @Step
    protected void clickByUsingPlaywright(String selector) {
        this.getByUsingPlaywright(selector).click();
    }

    @Step
    protected void fillByUsingPlaywright(String selector, String valueToFill){
        this.getByUsingPlaywright(selector).fill(valueToFill);
    }


    @Step
    protected String getTextByUsingPlaywright(String selector) {
        return this.getByUsingPlaywright(selector).textContent();
    }

    @Step
    protected String getValueByUsingPlaywright(String selector) {
        return this.getByUsingPlaywright(selector).inputValue();
    }

    @Step
    protected void assertTextByUsingPlaywright(String expectedText, String selector) {
        assertThat(getByUsingPlaywright(selector)).hasText(expectedText);
    }

    @Step
    protected void navigateToExerciseUsingPlaywright(String exerciseName) {
        page.navigate(url + exerciseName + "?seed=" + testSeed);
    }
}
