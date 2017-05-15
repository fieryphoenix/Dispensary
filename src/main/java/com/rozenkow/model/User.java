package com.rozenkow.model;

public class User {
  private String login;
  private String password;
  private String displayName;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

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
        "login='" + login + '\'' +
        ", displayName='" + displayName + '\'' +
        '}';
  }
}
