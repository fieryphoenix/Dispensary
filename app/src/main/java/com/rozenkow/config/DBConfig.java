package com.rozenkow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

/**
 * Created by Poul Rozenkow.
 */
@Configuration
@EnableMongoAuditing
public class DBConfig {

  @Bean
  @Profile("InitDB")
  public Jackson2RepositoryPopulatorFactoryBean usersPopulator() {
    Resource sourceData = new ClassPathResource("data.json");

    Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
    // Set a custom ObjectMapper if Jackson customization is needed
//    factory.setObjectMapper(…);
    factory.setResources(new Resource[]{sourceData});
    return factory;
  }
}
