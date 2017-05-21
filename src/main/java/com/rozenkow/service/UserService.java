package com.rozenkow.service;

import com.rozenkow.db.UserRepository;
import com.rozenkow.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.rozenkow.util.EncryptUtils.createHashSaltForPassword;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

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
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRepository.findDistinctFirstByUsernameIgnoreCase(userName);
    if (user == null) {
      throw new UsernameNotFoundException("Not found");
    }
    return user;
  }
}
