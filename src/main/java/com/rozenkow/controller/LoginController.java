package com.rozenkow.controller;

import com.rozenkow.model.User;
import com.rozenkow.service.UserService;
import com.rozenkow.validator.UserFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

  private final Logger logger = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private UserFormValidator userFormValidator;

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(userFormValidator);
  }

  @RequestMapping(method = RequestMethod.GET)
  public String login() {
    return "login";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String doLogin(@ModelAttribute("UserForm") @Validated User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

    logger.debug("doLogin() : {}", user);

    if (result.hasErrors()) {
//            populateDefaultModel(model);
      return "login";
    } else {

      // Add message to flash scope
      redirectAttributes.addFlashAttribute("css", "success");
      redirectAttributes.addFlashAttribute("msg", "Successfully logged-in!");

//            userService.saveOrUpdate(user);

      // POST/REDIRECT/GET
      model.addAttribute("name", user.getLogin());
      return "redirect:/hello/";

      // POST/FORWARD/GET
      // return "user/list";

    }
  }
}
