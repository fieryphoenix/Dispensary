package com.rozenkow.validator;


import com.rozenkow.model.User;
import com.rozenkow.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class UserFormValidator implements Validator {
  private final Logger logger = LoggerFactory.getLogger(UserFormValidator.class);

  private final UserService userService;

  @Autowired
  public UserFormValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    User user = (User) target;

    rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty.userForm.login");
    rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");

    try {
      boolean loginSuccess = userService.checkLogin(user);
      if (!loginSuccess) {
        errors.reject("login", "Failed.userForm.login");
      }
    } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
      logger.error("Failed to check user", e);
    }
  }

}
