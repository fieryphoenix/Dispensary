package com.rozenkow.service;

import com.rozenkow.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Poul Rozenkow.
 */
public interface UserService extends UserDetailsService {
  boolean checkLogin(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;

  void saveUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;
}
