package com.example.springboottests.config;

import com.microsoft.playwright.*;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Lazy
@DependsOn()
@Configuration
@Scope("singleton")
public class PlaywrightConfig implements DisposableBean {

    @Value("${webdriver.debug}")
    private String webDriverDebug;
    @Autowired(required = false)
    private WebDriver driver;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    @Override
    public void destroy() throws Exception {
        page.close();
        browserContext.close();
        browser.close();
        playwright.close();
    }

    @Bean
    public Page getPage() {
        Playwright.CreateOptions createOptions = new Playwright.CreateOptions();
        playwright = Playwright.create(createOptions);
        if (SeleniumWebSocketAdapter.getCdpCapability() != null) {
            browser = playwright.chromium().connectOverCDP(SeleniumWebSocketAdapter.getCdpCapability());
        } else {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        }

        if (browser.contexts().isEmpty()) {
            browserContext = browser.newContext(new Browser.NewContextOptions());
        } else {
            browserContext = browser.contexts().getFirst();
        }

        if (browserContext.pages().isEmpty()) {
            page = browserContext.newPage();
        } else {
            page = browserContext.pages().getFirst();
        }
        return this.page;
    }
}
