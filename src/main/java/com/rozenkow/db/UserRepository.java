package com.rozenkow.db;

import com.rozenkow.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Poul Rozenkow on 5/20/2017.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

  @Query("{username: ?0}")
  User findDistinctFirstByUsernameIgnoreCase(String userName);
}