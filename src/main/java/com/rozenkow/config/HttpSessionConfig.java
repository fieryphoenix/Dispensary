package com.rozenkow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.mongo.JdkMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

/**
 * Created by Poul Rozenkow.
 */
@EnableMongoHttpSession
public class HttpSessionConfig {
  @Bean
  public JdkMongoSessionConverter jdkMongoSessionConverter() {
    return new JdkMongoSessionConverter();
  }
}
