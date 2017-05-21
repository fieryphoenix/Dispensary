package com.rozenkow;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * Created by Poul Rozenkow on 5/21/2017.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@Profile("!https")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
    auth
        .inMemoryAuthentication()
        .withUser("user1").password("user1Pass").roles("USER");
    // @formatter:on
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    // @formatter:off
    http
        .authorizeRequests()
          .antMatchers("/login*", "/", "/index", "/css/**", "/js/**").permitAll()
          .anyRequest().authenticated()
        .and()
        .formLogin()
          .loginPage("/login")
        .successHandler(new SavedRequestAwareAuthenticationSuccessHandler());
    // @formatter:on
  }
}
