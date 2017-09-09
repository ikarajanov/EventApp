package org.eventapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * Jpa configuration class.
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.eventapp.repositories")
public class JpaConfiguration {

}