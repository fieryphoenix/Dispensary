package com.rozenkow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Poul Rozenkow.
 */

//@Configuration
//@EnableWebSecurity
//@EnableGlobalAuthentication
//@Profile("https")
public class ChannelSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userService;

  @Autowired
  public ChannelSecurityConfig(UserDetailsService userService) {
    this.userService = userService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
        .authorizeRequests()
          .antMatchers( "/css/**", "/js/**", "/index", "/", "/tld/**").permitAll()
          .antMatchers("/user/**").hasRole("USER")
          .anyRequest().permitAll()//fixme
          .and()
        .requiresChannel()
          .antMatchers("/login*", "/perform_login").requiresSecure()
          .anyRequest().requiresInsecure()
        .and()
          .sessionManagement()
          .sessionFixation()
        .none()
        .and()
        .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/login/processing")
          .defaultSuccessUrl("/index", true)
          .failureUrl("/login?error=true")
          .and()
        .logout()
          .logoutUrl("/logout")
          .deleteCookies("remember-me")
          .logoutSuccessUrl("/")
          .deleteCookies("JSESSIONID")
          .and();
//        .rememberMe();
    // @formatter:on
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
    auth
        .userDetailsService(userService).and()
        .inMemoryAuthentication()
          .withUser("user1").password("user1Pass").roles("USER")
          .and()
          .withUser("user2").password("user2Pass").roles("USER");
    // @formatter:on
  }
}
