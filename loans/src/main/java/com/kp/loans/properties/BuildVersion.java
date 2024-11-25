package com.kp.loans.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "build")
public record BuildVersion(String version) {
}
