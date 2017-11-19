package com.rozenkow.model;

/**
 * Created by Poul Rozenkow.
 */
public enum Role {
  Doctor("ROLE_USER"), Operator("ROLE_USER", "ROLE_OPERATOR"), Admin("ROLE_USER", "ROLE_OPERATOR", "ROLE_ADMIN");

  private final String[] grantedRoles;

  Role(String... grantedRoles) {
    this.grantedRoles = grantedRoles;
  }

  public String[] getGrantedRoles() {
    return grantedRoles;
  }
}
