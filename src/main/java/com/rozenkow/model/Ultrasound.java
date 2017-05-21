package com.rozenkow.model;

import java.time.LocalDate;

/**
 * Created by Poul Rozenkow.
 */
public class Ultrasound {
  private String note;
  private UltrasoundType type;
  private LocalDate recordsDate;

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public UltrasoundType getType() {
    return type;
  }

  public void setType(UltrasoundType type) {
    this.type = type;
  }

  public LocalDate getRecordsDate() {
    return recordsDate;
  }

  public void setRecordsDate(LocalDate recordsDate) {
    this.recordsDate = recordsDate;
  }

  @Override
  public String toString() {
    return "Ultrasound{" +
           "note='" + note + '\'' +
           ", type=" + type +
           ", recordsDate=" + recordsDate +
           '}';
  }
}
