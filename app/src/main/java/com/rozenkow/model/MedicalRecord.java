package com.rozenkow.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Document(collection = "medrecords")
public class MedicalRecord extends AuditEntity {
  private Person patient;
  private String number;
  private List<Disease> diseases;
  private List<Ultrasound> ultrasounds;
  private List<String> medicalExaminations;
  private List<Visit> visits;
  private List<Analysis> MRIs;
  private List<Analysis> CTScans;

  public MedicalRecord() {
    patient = new Person();
    diseases = new ArrayList<>();
    ultrasounds = new ArrayList<>();
    medicalExaminations = new ArrayList<>();
    visits = new ArrayList<>();
    MRIs = new ArrayList<>();
    CTScans = new ArrayList<>();
  }

  public Person getPatient() {
    return patient;
  }

  public void setPatient(Person patient) {
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

  public List<Visit> getVisits() {
    return visits;
  }

  public void setVisits(List<Visit> visits) {
    this.visits = visits;
  }

  public List<Analysis> getMRIs() {
    return MRIs;
  }

  public void setMRIs(List<Analysis> MRIs) {
    this.MRIs = MRIs;
  }

  public List<Analysis> getCTScans() {
    return CTScans;
  }

  public void setCTScans(List<Analysis> CTScans) {
    this.CTScans = CTScans;
  }

  public List<? extends Analysis> getAnalysis(Analysis.Type type) {
    switch (type) {
      case MRI:
        return getMRIs();
      case CTScan:
        return getCTScans();
      case Ultrasound:
        return getUltrasounds();
      default:
        throw new UnsupportedOperationException("Unsupported Type");
    }
  }

  public void addAnalysis(Analysis.Type type) {
    switch (type) {
      case MRI:
        getMRIs().add(new Analysis());
        break;
      case CTScan:
        getCTScans().add(new Analysis());
        break;
      case Ultrasound:
        getUltrasounds().add(new Ultrasound());
        break;
      default:
        throw new UnsupportedOperationException("Unsupported Type");
    }
  }

  @Override
  public String toString() {
    return "MedicalRecord{" +
           "patient=" + patient +
           ", number='" + number + '\'' +
           ", diseases=" + diseases +
           ", ultrasounds=" + ultrasounds +
           ", medicalExaminations=" + medicalExaminations +
           ", visits=" + visits +
           ", MRIs=" + MRIs +
           ", CTScans=" + CTScans +
           "} " + super.toString();
  }
}
