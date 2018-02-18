package com.rozenkow.model;

import java.util.Date;

/**
 * Created by Poul Rozenkow.
 */
public class Analysis {
  private String notes;
  private Date recordDate;

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Date getRecordDate() {
    return recordDate;
  }

  public void setRecordDate(Date recordDate) {
    this.recordDate = recordDate;
  }

  @Override
  public String toString() {
    return "Analysis{" +
           "notes='" + notes + '\'' +
           ", recordDate=" + recordDate +
           '}';
  }

  public enum Type {
    Ultrasound,
    MRI,
    CTScan
  }
}
