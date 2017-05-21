package com.rozenkow.db;

import com.rozenkow.model.User;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;

/**
 * Created by Poul Rozenkow.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {
  @Autowired
  UserRepository userRepository;

  @Test
  public void containsAdmin() {

    User admin = userRepository.findDistinctFirstByUsernameIgnoreCase("Admin");
    assertThat(admin, Matchers.notNullValue());
  }
}