package com.projects.adapters.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackageClasses = SpringPersistenceConfiguration.class)
@EntityScan(basePackageClasses = SpringPersistenceConfiguration.class)
@ComponentScan(basePackageClasses = SpringPersistenceConfiguration.class)
@EnableTransactionManagement
public class SpringPersistenceConfiguration {
}