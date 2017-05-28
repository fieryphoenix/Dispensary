package com.rozenkow.model;

import java.util.Date;

/**
 * Created by Poul Rozenkow.
 */
public class Ultrasound {
  private String notes;
  private UltrasoundType type;
  private Date recordDate;

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public UltrasoundType getType() {
    return type;
  }

  public void setType(UltrasoundType type) {
    this.type = type;
  }

  public Date getRecordDate() {
    return recordDate;
  }

  public void setRecordDate(Date recordDate) {
    this.recordDate = recordDate;
  }

  @Override
  public String toString() {
    return "Ultrasound{" +
           "notes='" + notes + '\'' +
           ", type=" + type +
           ", recordDate=" + recordDate +
           '}';
  }
}
