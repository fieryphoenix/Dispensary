package com.rozenkow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Poul Rozenkow on 5/15/2017.
 */
@Controller
public class WelcomeController {

  @RequestMapping("/")
  public String welcome() {
    return "redirect:/index";
  }

  @RequestMapping("/index")
  public String index() {
    return "index";
  }
}
