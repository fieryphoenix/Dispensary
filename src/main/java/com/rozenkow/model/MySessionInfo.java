package com.rozenkow.model;

import com.rozenkow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by Poul Rozenkow.
 */
@Component
@Scope("session")
public class MySessionInfo {
  private UserDetails user;
  private final UserService userService;

  @Autowired
  public MySessionInfo(UserService userService) {
    this.userService = userService;
  }

  protected UserDetails getCurrentUser() {
    if (user == null) {
      user = userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    return user;
  }

}
