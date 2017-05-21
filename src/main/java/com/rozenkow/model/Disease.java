package com.rozenkow.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Poul Rozenkow.
 */
public class Disease implements Serializable {
  private String name;
  private LocalDate from;
  private LocalDate to;
  private String notes;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getFrom() {
    return from;
  }

  public void setFrom(LocalDate from) {
    this.from = from;
  }

  public LocalDate getTo() {
    return to;
  }

  public void setTo(LocalDate to) {
    this.to = to;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  @Override
  public String toString() {
    return "Disease{" +
           "name='" + name + '\'' +
           ", from=" + from +
           ", to=" + to +
           ", notes='" + notes + '\'' +
           '}';
  }
}
