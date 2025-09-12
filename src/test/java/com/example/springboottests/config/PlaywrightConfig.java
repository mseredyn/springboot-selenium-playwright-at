package com.example.springboottests.config;

import com.microsoft.playwright.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@Scope("singleton")
public class PlaywrightConfig implements DisposableBean {

    @Autowired
    private SeleniumWebSocketAdapter seleniumWebSocketAdapter;

    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    @Override
    public void destroy() {
        Page page = pageThreadLocal.get();
        BrowserContext context = contextThreadLocal.get();
        Browser browser = browserThreadLocal.get();
        Playwright playwright = playwrightThreadLocal.get();

        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();

        pageThreadLocal.remove();
        contextThreadLocal.remove();
        browserThreadLocal.remove();
        playwrightThreadLocal.remove();
    }

    public void init() {
        Playwright.CreateOptions createOptions = new Playwright.CreateOptions();
        Playwright playwright = Playwright.create(createOptions);
        Browser browser;
        if (seleniumWebSocketAdapter.getCdpCapability() != null) {
            browser = playwright.chromium()
                    .connectOverCDP(seleniumWebSocketAdapter.getCdpCapability());
        } else {
            browser = playwright.chromium()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setChannel("chrome"));
        }
        BrowserContext browserContext;
        if (browser.contexts().isEmpty()) {
            browserContext = browser
                    .newContext(new Browser.NewContextOptions());
        } else {
            browserContext = browser.contexts().getFirst();
        }
        Page page;
        if (browserContext.pages().isEmpty()) {
            page = browserContext.newPage();
        } else {
            page = browserContext.pages().getFirst();
        }
        playwrightThreadLocal.set(playwright);
        browserThreadLocal.set(browser);
        contextThreadLocal.set(browserContext);
        pageThreadLocal.set(page);
    }

    public Page getPage() {
        if(pageThreadLocal.get() == null) {
            this.init();
        }
        return pageThreadLocal.get();
    }
}
