package org.eventapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
  DatabaseConfiguration.class,
  JpaConfiguration.class
})
public class EventAppConfiguration {

}
