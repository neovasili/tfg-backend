package com.juanmanuelruizfernandez.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ConfigurationProperties {

    private String dynamoTableName = "tfg-tickets";

    public ConfigurationProperties() {
    }

    public String getDynamoTableName() {
        return dynamoTableName;
    }
}
