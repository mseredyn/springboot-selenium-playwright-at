package com.example.springboottests.config.scope;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ThreadLocalScopeConfig {
    @Bean
    public static CustomScopeConfigurer threadLocalScopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        HashMap<String, Object> scopes = Maps.newHashMap();
        scopes.put("thread-local", new ThreadLocalScope());
        configurer.setScopes(scopes);
        return configurer;
    }
}
