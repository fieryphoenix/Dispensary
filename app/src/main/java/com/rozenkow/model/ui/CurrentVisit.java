package com.rozenkow.model.ui;

import com.rozenkow.model.MedicalRecord;
import com.rozenkow.model.Visit;

import java.io.Serializable;

/**
 * Created by Poul Rozenkow.
 */
public class CurrentVisit implements Serializable {
  private Visit visit;
  private MedicalRecord medicalRecord;

  public CurrentVisit(Visit visit, MedicalRecord medicalRecord) {
    this.visit = visit;
    this.medicalRecord = medicalRecord;
  }

  public Visit getVisit() {
    return visit;
  }

  public MedicalRecord getMedicalRecord() {
    return medicalRecord;
  }
}
