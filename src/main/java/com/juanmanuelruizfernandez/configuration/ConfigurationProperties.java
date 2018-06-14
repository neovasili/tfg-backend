package com.juanmanuelruizfernandez.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationProperties {

	private String dynamoDBTableName = "tfg-tickets";

	public ConfigurationProperties() {
	}

	public String getDynamoDBTableName() {
		return dynamoDBTableName;
	}
}
