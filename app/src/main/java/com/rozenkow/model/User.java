package com.rozenkow.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Document(collection = "users")
public class User extends Worker implements UserDetails {

  private String username;
  private String passwordHash;
  private String passwordSalt;
  @Transient
  private String password;
  private List<? extends GrantedAuthority> authorities;

  public User() {
  }

  @PersistenceConstructor
  public User(String id, String username, String passwordHash, String passwordSalt) {
    setId(id);
    this.username = username;
    this.passwordHash = passwordHash;
    this.passwordSalt = passwordSalt;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public String getUsername() {
    return username;
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
  public boolean isAccountNonExpired() {
    return true;
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(List<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String getPassword() {
    return password == null ? getPasswordHash() : password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getDisplayName() {
    if (StringUtils.isBlank(getFirstName()) || StringUtils.isBlank(getLastName())) {
      return username;
    }
    return getFirstName() + " " + getLastName();
  }

  @Override
  public String toString() {
    return "User{" +
           "username='" + username + '\'' +
           ", displayName='" + getDisplayName() + '\'' +
           ", authorities=" + authorities +
           "} " + super.toString();
  }
}
