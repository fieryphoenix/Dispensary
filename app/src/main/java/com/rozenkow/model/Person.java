package com.rozenkow.model;

import org.springframework.data.mongodb.core.index.TextIndexed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
public class Person extends AuditEntity {
  @TextIndexed
  private String firstName;
  @TextIndexed
  private String lastName;
  @TextIndexed
  private String middleName;
  private String passportNumber;
  private String passportSeries;
  private Date birthDate;
  private Sex sex;
  private Address address1;
  private List<String> phones;

  public Person() {
    phones = new ArrayList<>();
    address1 = new Address();
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

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
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
    return "Person{" +
           "firstName='" + firstName + '\'' +
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
