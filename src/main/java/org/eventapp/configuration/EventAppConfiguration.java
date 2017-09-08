package org.eventapp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
// @ComponentScan("site servici i repositoria")
@Import({
   // DatabaseConfiguration.class
})
public class EventAppConfiguration {

}
