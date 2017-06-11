package com.rozenkow.model;

/**
 * Created by Poul Rozenkow.
 */
public class Worker extends Person {
  private Roles role;
  private Speciality speciality;

  public Roles getRole() {
    return role;
  }

  public void setRole(Roles role) {
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
