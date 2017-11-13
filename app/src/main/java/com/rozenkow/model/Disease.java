package com.rozenkow.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Poul Rozenkow.
 */
public class Disease implements Serializable {
  private String name;
  private Date from;
  private Date to;
  private String notes;

  public Disease() {
    from = new Date();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getFrom() {
    return from;
  }

  public void setFrom(Date from) {
    this.from = from;
  }

  public Date getTo() {
    return to;
  }

  public void setTo(Date to) {
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
