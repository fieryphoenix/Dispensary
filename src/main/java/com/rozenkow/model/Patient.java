package com.rozenkow.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Document
public class Patient {
  private String id;
  private String firstName;
  private String lastName;
  private String middleName;
  private String passportNumber;
  private String passportSeries;
  private LocalDate birthDate;
  private Sex sex;
  private Address address1;
  private List<String> phones;

  public Patient() {
    phones = new ArrayList<>();
    address1 = new Address();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getPassportNumber() {
    return passportNumber;
  }

  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
  }

  public String getPassportSeries() {
    return passportSeries;
  }

  public void setPassportSeries(String passportSeries) {
    this.passportSeries = passportSeries;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  public Address getAddress1() {
    return address1;
  }

  public void setAddress1(Address address1) {
    this.address1 = address1;
  }

  public List<String> getPhones() {
    return phones;
  }

  public void setPhones(List<String> phones) {
    this.phones = phones;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }

  @Override
  public String toString() {
    return "Patient{" +
           "id='" + id + '\'' +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", middleName='" + middleName + '\'' +
           ", passportNumber='" + passportNumber + '\'' +
           ", passportSeries='" + passportSeries + '\'' +
           ", birthDate=" + birthDate +
           ", sex=" + sex +
           ", address1=" + address1 +
           ", phones=" + phones +
           '}';
  }
}
