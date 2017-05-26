package com.rozenkow.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Document(collection = "medrecords")
public class MedicalRecord extends AuditEntity {
  private Patient patient;
  private String number;
  private List<Disease> diseases;
  private List<Ultrasound> ultrasounds;
  private List<String> medicalExaminations;

  public MedicalRecord() {
    patient = new Patient();
    diseases = new ArrayList<>();
    ultrasounds = new ArrayList<>();
    medicalExaminations = new ArrayList<>();
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

  @Override
  public String toString() {
    return "MedicalRecord{" +
           "id='" + getId() + '\'' +
           ", patient=" + patient +
           ", number='" + number + '\'' +
           ", created=" + getCreatedDate() +
           ", modified=" + getUpdatedDate() +
           ", diseases=" + diseases +
           ", ultrasounds=" + ultrasounds +
           ", medicalExaminations=" + medicalExaminations +
           '}';
  }
}
