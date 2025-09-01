package com.example.springboottests.config;

import lombok.SneakyThrows;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.URI;

@Configuration
@Scope("singleton")
public class SeleniumConfig implements DisposableBean {
    private static ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);
    private static final String SELENIUM_GRID_HUB_URL = "/wd/hub";
    @Autowired
    private SeleniumWebSocketAdapter seleniumWebSocketAdapter;

    @Override
    public void destroy() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
        seleniumWebSocketAdapter.clearCdpCapability();
    }

    @SneakyThrows
    public void init(){
        String gridUrl = System.getenv("SELENIUM_REMOTE_URL");
        ChromeOptions options = new ChromeOptions();
        WebDriver webDriver;
        if (gridUrl != null && !gridUrl.isEmpty()) {
            if(!gridUrl.endsWith(SELENIUM_GRID_HUB_URL)){
                gridUrl = gridUrl + SELENIUM_GRID_HUB_URL;
            }
            webDriver = new RemoteWebDriver(new URI(gridUrl).toURL(), options);
        } else {
            webDriver = new ChromeDriver(options);
        }
        driver.set(webDriver);
        String cdp = ((HasCapabilities) webDriver).getCapabilities().getCapability("se:cdp").toString();
        seleniumWebSocketAdapter.setCdpCapability(cdp);
    }

    public WebDriver getDriver() {
        if (driver.get() == null) {
            this.init();
        }
        return driver.get();
    }
}

