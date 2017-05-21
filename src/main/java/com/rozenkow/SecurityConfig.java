package com.rozenkow;

import com.rozenkow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Poul Rozenkow on 5/21/2017.
 */

//@Configuration
//@EnableWebSecurity
//@EnableGlobalAuthentication
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;

  @Autowired
  public SecurityConfig(UserService userService) {
    this.userService = userService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
        .authorizeRequests()
          .antMatchers( "/css/**", "/js/**", "/index").permitAll()
//          .antMatchers("/user/**").hasRole("USER")
//          .anyRequest().authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
//          .failureForwardUrl("/login")
//          .loginProcessingUrl("/login")
//          .failureUrl("/login")
          .permitAll()
          .and()
        .logout()
          .logoutUrl("/logout")
          .deleteCookies("remember-me")
          .logoutSuccessUrl("/")
          .permitAll()
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
          .withUser("user").password("password").roles("USER")
          .and()
          .withUser("admin").password("password").roles("USER", "ADMIN");
    // @formatter:on
  }


  @Override
  public void configure(WebSecurity web) throws Exception {
    // @formatter:off
    web.debug(true)
        .ignoring()
        .antMatchers("/css/**", "/js/**");
    // @formatter:on
  }
}
