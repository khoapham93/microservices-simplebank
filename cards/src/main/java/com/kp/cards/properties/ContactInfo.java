package com.kp.cards.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@ConfigurationProperties(prefix = "contact")
@Getter
@Setter
public class  ContactInfo{
        private String message;
        private Map<String, String> contactDetails;
}
