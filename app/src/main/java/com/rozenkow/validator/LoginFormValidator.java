package com.rozenkow.validator;


import com.rozenkow.model.User;
import com.rozenkow.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class LoginFormValidator implements Validator {
  private final Logger logger = LoggerFactory.getLogger(LoginFormValidator.class);

  private final UserService userService;

  @Autowired
  public LoginFormValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    User user = (User) target;

    rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userForm.username");
    rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");

  }

}
