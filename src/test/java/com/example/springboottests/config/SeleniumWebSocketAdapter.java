package com.example.springboottests.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope("singleton")
public class SeleniumWebSocketAdapter {
    private static ThreadLocal<String> cdpUrl = ThreadLocal.withInitial(() -> null);

    public void setCdpCapability(String cdp) {
        cdpUrl.set(cdp);
    }

    public String getCdpCapability() {
        return cdpUrl.get();
    }

    public void clearCdpCapability() {
        cdpUrl.remove();
    }
}
