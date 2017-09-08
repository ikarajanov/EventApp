package org.eventapp;

import org.eventapp.configuration.EventAppConfiguration;
import org.eventapp.configuration.WebConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class App extends AbstractAnnotationConfigDispatcherServletInitializer {

  protected Class<?>[] getRootConfigClasses() {
    return new Class[]{
      EventAppConfiguration.class
    };
  }
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{
        WebConfiguration.class
    };
  }

  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}
