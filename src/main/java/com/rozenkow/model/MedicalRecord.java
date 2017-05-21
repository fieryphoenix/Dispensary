package com.rozenkow.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Document
public class MedicalRecord {
  private String id;
  private Patient patient;
  private String number;
  private LocalDateTime created;
  private LocalDateTime modified;
  private User createdBy;
  private User modifiedBy;
  private List<Disease> diseases;
  private List<Ultrasound> ultrasounds;
  private List<String> medicalExaminations;

  public MedicalRecord() {
    patient = new Patient();
    diseases = new ArrayList<>();
    ultrasounds = new ArrayList<>();
    medicalExaminations = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  public LocalDateTime getModified() {
    return modified;
  }

  public void setModified(LocalDateTime modified) {
    this.modified = modified;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }

  public User getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(User modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public List<Disease> getDiseases() {
    return diseases;
  }

  public void setDiseases(List<Disease> diseases) {
    this.diseases = diseases;
  }

  public List<Ultrasound> getUltrasounds() {
    return ultrasounds;
  }

  public void setUltrasounds(List<Ultrasound> ultrasounds) {
    this.ultrasounds = ultrasounds;
  }

  public List<String> getMedicalExaminations() {
    return medicalExaminations;
  }

  public void setMedicalExaminations(List<String> medicalExaminations) {
    this.medicalExaminations = medicalExaminations;
  }
}
