package com.rozenkow.model;

/**
 * Created by Poul Rozenkow.
 */
public class Worker extends Person {
  private Role role;
  private Speciality speciality;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Speciality getSpeciality() {
    return speciality;
  }

  public void setSpeciality(Speciality speciality) {
    this.speciality = speciality;
  }

  @Override
  public String toString() {
    return "Worker{" +
           "role=" + role +
           ", speciality='" + speciality + '\'' +
           "} " + super.toString();
  }
}
