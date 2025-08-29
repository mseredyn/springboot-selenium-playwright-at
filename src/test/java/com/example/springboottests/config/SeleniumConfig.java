package com.example.springboottests.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Lazy
@Scope("singleton")
@Configuration
public class SeleniumConfig implements DisposableBean {
    private ChromeDriver driver;

    @Override
    public void destroy() {
        this.driver.close();
    }

    @Bean
    public ChromeDriver getDriver() {
        ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort().build();
        ChromeOptions options = new ChromeOptions();
        this.driver = new ChromeDriver(service, options);
        SeleniumWebSocketAdapter.setCdpCapability(this.driver.getCapabilities().getCapability("se:cdp").toString());

        return this.driver;
    }
}

