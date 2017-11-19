package com.rozenkow.controller;

import com.rozenkow.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Poul Rozenkow.
 */
//@ControllerAdvice
public class CurrentUserControllerAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

  @ModelAttribute("currentUser")
  public User getCurrentUser(Authentication authentication) {
    return (authentication == null) ? null : (User) authentication.getPrincipal();
  }
}