package com.tec.vuepractice.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "novel.cors")
public record CorsProperties(List<String> allowOrigins) {

}
