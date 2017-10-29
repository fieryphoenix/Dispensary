package com.rozenkow.db;

import com.rozenkow.model.Role;
import com.rozenkow.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

  @Query("{username: ?0}")
  User findDistinctFirstByUsernameIgnoreCase(String userName);

  List<User> findDistinctByRole(Role role);
}
