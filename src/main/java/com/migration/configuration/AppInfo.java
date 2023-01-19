package com.migration.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Data
@ConfigurationProperties(prefix = "migration.v1")
@Configuration
public class AppInfo implements WebMvcConfigurer {
    private String useragent;
    private String geturl;
    private String posturl;
}
