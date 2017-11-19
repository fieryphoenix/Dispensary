package com.rozenkow.config;

import com.rozenkow.service.CustomPasswordEncoder;
import com.rozenkow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Poul Rozenkow.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Profile("!https")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserService userService;

  @Autowired
  public SecurityConfig(UserService userService) {
    this.userService = userService;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
    auth
        .userDetailsService(userService)
        .and()
        .authenticationProvider(authProvider())
        .eraseCredentials(true);
//        .inMemoryAuthentication()
//        .withUser("user").password("password").roles("USER");
    // @formatter:on
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    // @formatter:off
    http
        .authorizeRequests()
          .antMatchers( "/", "/index", "/css/**", "/js/**", "/tld/**", "/bower_components/**").permitAll()
          .antMatchers( "/login/**").permitAll()
          .anyRequest().authenticated()
        .and()
        .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/login-processing")
          .defaultSuccessUrl("/index", true)
          .failureUrl("/login?error=true")
        .and()
        .logout()
          .logoutUrl("/logout")
          .clearAuthentication(true)
          .deleteCookies("JSESSIONID")
          .permitAll()
        .and();
    // @formatter:on
  }

  @Bean
  public AbstractUserDetailsAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    authProvider.setSaltSource(saltSource());
    return authProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new CustomPasswordEncoder();
  }

  @Bean
  public SaltSource saltSource() {
    return userService;
  }
}
