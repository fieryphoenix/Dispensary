package com.rozenkow.service;

import com.rozenkow.model.MedicalRecord;
import com.rozenkow.model.User;
import com.rozenkow.model.ui.SearchCriteria;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
public interface UserService extends UserDetailsService {
  boolean checkLogin(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;

  User saveUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;

  List<User> searchRecords(SearchCriteria searchCriteria);

  User getById(String id);

  boolean removeUser(String id);
}
