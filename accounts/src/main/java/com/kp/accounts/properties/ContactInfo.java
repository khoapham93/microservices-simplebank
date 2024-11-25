package com.kp.accounts.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "contact")
public record ContactInfo(
        String message,
        Map<String, String> contactDetails
) {
}
