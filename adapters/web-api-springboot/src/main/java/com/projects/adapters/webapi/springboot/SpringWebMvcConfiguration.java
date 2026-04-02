package com.projects.adapters.webapi.springboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(
    basePackages = {"com.projects.adapters.webapi.springboot"}
)
public class SpringWebMvcConfiguration implements WebMvcConfigurer {
}
