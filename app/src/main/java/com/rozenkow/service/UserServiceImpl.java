package com.rozenkow.service;

import com.rozenkow.db.UserRepository;
import com.rozenkow.model.Role;
import com.rozenkow.model.User;
import com.rozenkow.model.Worker;
import com.rozenkow.model.ui.SearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.rozenkow.util.EncryptUtils.createHashSaltForPassword;

@Service("userDetailsService")
class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User saveUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
    User existingUser = userRepository.findDistinctFirstByUsernameIgnoreCase(user.getUsername());
    if (existingUser != null && StringUtils.isBlank(user.getId())) {
      throw new RuntimeException("user exists");
    }
    if (existingUser == null || StringUtils.isNoneBlank(user.getPassword())) {
      Pair<String, String> hashAndSalt = createHashSaltForPassword(user.getPassword().trim(), null);
      user.setPasswordHash(hashAndSalt.getFirst());
      user.setPasswordSalt(hashAndSalt.getSecond());
    } else {
      user.setPasswordHash(existingUser.getPasswordHash());
      user.setPasswordSalt(existingUser.getPasswordSalt());
    }
    List<GrantedAuthority> authorities = new ArrayList<>();
    if (user.getRole() != null) {
      for (String role : user.getRole().getGrantedRoles()) {
        authorities.add(new SimpleGrantedAuthority(role));
      }
    }
    user.setAuthorities(authorities);

    return userRepository.save(user);
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
  public boolean removeUser(String id) {
    User userToDelete = userRepository.findOne(id);
    if (userToDelete != null) {
      userRepository.delete(userToDelete);
      return true;
    }
    return false;
  }

  @Override
  public List<Worker> getAllWorkers() {
    List<User> doctors = userRepository.findDistinctByRole(Role.Doctor);
    List<User> operators = userRepository.findDistinctByRole(Role.Operator);
    List<Worker> workers = doctors.stream().map(u -> (Worker) u).collect(Collectors.toList());
    workers.addAll(operators.stream().map(u -> (Worker) u).collect(Collectors.toList()));
    return workers;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRepository.findDistinctFirstByUsernameIgnoreCase(userName);
    if (user == null) {
      throw new UsernameNotFoundException("Not found");
    }
    return user;
  }

  @Override
  public Object getSalt(UserDetails user) {
    return ((User) user).getPasswordSalt();
  }
}
