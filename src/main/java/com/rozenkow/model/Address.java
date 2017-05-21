package com.rozenkow.model;

import java.io.Serializable;

/**
 * Created by Poul Rozenkow.
 */
public class Address implements Serializable {
  private String city;
  private String country;
  private String street;
  private String house;
  private String flat;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  public String getFlat() {
    return flat;
  }

  public void setFlat(String flat) {
    this.flat = flat;
  }

  @Override
  public String toString() {
    return "Address{" +
           "city='" + city + '\'' +
           ", country='" + country + '\'' +
           ", street='" + street + '\'' +
           ", house='" + house + '\'' +
           ", flat='" + flat + '\'' +
           '}';
  }
}
