package org.eventapp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "org.eventapp.persistence.service")
@Import({
  DatabaseConfiguration.class,
  JpaConfiguration.class
})
public class EventAppConfiguration {

}
