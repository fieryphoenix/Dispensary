package com.rozenkow.service;

import com.rozenkow.model.User;
import com.rozenkow.model.Worker;
import com.rozenkow.model.ui.SearchCriteria;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
public interface UserService extends UserDetailsService, SaltSource {

  User saveUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException;

  List<User> searchRecords(SearchCriteria searchCriteria);

  User getById(String id);

  boolean removeUser(String id);

  List<Worker> getAllWorkers();
}
