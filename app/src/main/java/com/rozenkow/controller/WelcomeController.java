package com.rozenkow.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Poul Rozenkow.
 */
@Controller
public class WelcomeController {

  @RequestMapping("/")
  public String welcome() {
    return "redirect:/index";
  }

  @RequestMapping("/index")
  public String index() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return "index";
  }
}
