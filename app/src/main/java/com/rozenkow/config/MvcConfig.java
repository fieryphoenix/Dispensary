package com.rozenkow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Poul Rozenkow.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
//    registry.addViewController("/index").setViewName("home");
//    registry.addViewController("/medrecord").setViewName("medrecord");
//    registry.addViewController("/login").setViewName("login");
//    registry.addViewController("/user").setViewName("user");
  }

}