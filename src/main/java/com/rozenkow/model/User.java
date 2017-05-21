package com.rozenkow.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by Poul Rozenkow on 5/15/2017.
 */
@Document(collection = "users")
public class User implements UserDetails {
  @Id
  private String id;
  private String username;
  private String passwordHash;
  private String passwordSalt;
  @Transient
  private String password;
  private String displayName;
  private List<SimpleGrantedAuthority> authorities;

  public User() {
  }

  @PersistenceConstructor
  public User(String id, String username, String passwordHash, String passwordSalt, String displayName) {
    this.id = id;
    this.username = username;
    this.passwordHash = passwordHash;
    this.passwordSalt = passwordSalt;
    this.displayName = displayName;
  }

  public User(String username, String password, String displayName) {
    this.username = username;
    this.password = password;
    this.displayName = displayName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public String getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return "User{" +
           "id='" + id + '\'' +
           ", username='" + username + '\'' +
           ", displayName='" + displayName + '\'' +
           ", authorities=" + authorities +
           '}';
  }
}
