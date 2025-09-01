package com.example.springboottests.pages;

import com.example.springboottests.config.SeleniumConfig;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Lazy
@Component
@Scope("thread-local")
@ConditionalOnBean(WebDriver.class)
public class BaseSeleniumPage {
    @Value("${base.url}")
    private String url;

    @Value("${test.seed}")
    private String testSeed;

    @Autowired
    private SeleniumConfig seleniumConfig;

    @Step
    protected WebElement getByUsingSelenium(String selector) {
        return seleniumConfig.getDriver().findElement(byChooser(selector));
    }

    @Step
    protected void clickByUsingSelenium(String selector){
        this.getByUsingSelenium(selector).click();
    }

    @Step
    protected void fillByUsingSelenium(String selector, String valueToFill){
        this.getByUsingSelenium(selector).clear();
        this.getByUsingSelenium(selector).sendKeys(valueToFill);
    }

    @Step
    protected String getTextByUsingSelenium(String selector) {
        return this.getByUsingSelenium(selector).getText();
    }

    @Step
    protected String getValueByUsingSelenium(String selector) {
        return this.getByUsingSelenium(selector).getAttribute("value");
    }

    @Step
    protected void assertTextByUsingSelenium(String expected, String selector){
        WebDriverWait wait = new WebDriverWait(seleniumConfig.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(byChooser(selector), expected));
    }

    @Step
    protected void navigateToExerciseUsingSelenium(String exerciseName) {
        seleniumConfig.getDriver().get(url + exerciseName + "?seed=" + testSeed);
    }

    private static By byChooser(String locator){
        if (locator.startsWith("/")){
            return By.xpath(locator);
        } else {
            return By.cssSelector(locator);
        }
    }
}
