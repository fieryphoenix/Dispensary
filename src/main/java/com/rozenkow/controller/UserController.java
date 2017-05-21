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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("UserForm")
public class UserController {
  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;
  private final UserFormValidator userFormValidator;

  @Autowired
  public UserController(UserService userService, UserFormValidator userFormValidator) {
    this.userService = userService;
    this.userFormValidator = userFormValidator;
  }

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(userFormValidator);
  }

  @RequestMapping(path = "/login", method = RequestMethod.GET)
  public String login(Model model) {
    model.addAttribute("UserForm", new User());
    model.addAttribute("Remember", false);
    return "login";
  }

  @RequestMapping(path = "/login-processing", method = RequestMethod.POST)
  public String doLogin(@ModelAttribute("UserForm") @Validated User user, BindingResult result,
                        RedirectAttributes redirectAttributes) {

    logger.debug("doLogin() : {}", user);

    if (result.hasErrors()) {
      return "login";
    } else {

      redirectAttributes.addFlashAttribute("css", "success");
      redirectAttributes.addFlashAttribute("msgKey", "Success.userForm.login");

//            userService.saveOrUpdate(user);

      // POST/REDIRECT/GET
      return "redirect:/index";
    }
  }

  @RequestMapping(path = "/logout", method = RequestMethod.GET)
  public String doLogout(Model model) {
    model.asMap().clear();
    return "/";
  }

}
