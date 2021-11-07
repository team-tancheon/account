package com.tancheon.account.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {
    private Map<String, Integer> asyncExecutor;
    private Map<String, String> mail;
}
