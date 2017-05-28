package com.rozenkow.service;

import com.rozenkow.db.UserRepository;
import com.rozenkow.model.User;
import com.rozenkow.model.ui.SearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static com.rozenkow.util.EncryptUtils.createHashSaltForPassword;

@Service("userDetailsService")
class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean checkLogin(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
    if (user == null) {
      return false;
    }
    User existingUser = userRepository.findDistinctFirstByUsernameIgnoreCase(user.getUsername());
    if (existingUser == null) {
      return false;
    }

    Pair<String, String> saltAndHash = createHashSaltForPassword(user.getPassword(), existingUser.getPasswordSalt());

    return StringUtils.equals(saltAndHash.getFirst(), existingUser.getPasswordHash()) && StringUtils.equals
        (saltAndHash.getSecond(), existingUser.getPasswordSalt());
  }

  @Override
  public void saveUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
    User existingUser = userRepository.findDistinctFirstByUsernameIgnoreCase(user.getUsername());
    if (existingUser != null) {
      throw new RuntimeException("user exists");
    }

    Pair<String, String> hashAndSalt = createHashSaltForPassword(user.getPassword(), null);
    User userToSave = new User(null, user.getUsername(), hashAndSalt.getFirst(), hashAndSalt.getSecond(), user
        .getDisplayName());
    userRepository.save(userToSave);
  }

  @Override
  public List<User> searchRecords(SearchCriteria searchCriteria) {
    return userRepository.findAll(); //fixme
  }

  @Override
  public User getById(String id) {
    return userRepository.findOne(id);
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRepository.findDistinctFirstByUsernameIgnoreCase(userName);
    if (user == null) {
      throw new UsernameNotFoundException("Not found");
    }
    return user;
  }
}
