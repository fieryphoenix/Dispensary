package com.rozenkow.service;

import com.rozenkow.db.MedicalRecordRepository;
import com.rozenkow.model.MedicalRecord;
import com.rozenkow.model.ui.SearchCriteria;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Service
class MedicalRecordServiceImpl implements MedicalRecordService {
  private final MedicalRecordRepository medicalRecordRepository;

  @Autowired
  MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
    this.medicalRecordRepository = medicalRecordRepository;
  }

  @Override
  public List<MedicalRecord> getRecords() {
    return medicalRecordRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "created")));
  }

  @Override
  public MedicalRecord getRecord(String id) {
    return medicalRecordRepository.findOne(id);
  }

  @Override
  public MedicalRecord saveRecord(MedicalRecord medicalRecord) {
    MedicalRecord recordsToSave = medicalRecordRepository.findOne(medicalRecord.getId());
    if (recordsToSave != null) {
      recordsToSave.setPatient(medicalRecord.getPatient());
      recordsToSave.setDiseases(medicalRecord.getDiseases());
      recordsToSave.setMedicalExaminations(medicalRecord.getMedicalExaminations());
      recordsToSave.setUltrasounds(medicalRecord.getUltrasounds());
    } else {
      recordsToSave = medicalRecord;
    }
    return medicalRecordRepository.save(recordsToSave);
  }

  @Override
  public boolean removeRecord(String id) {
    MedicalRecord recordsToSave = medicalRecordRepository.findOne(id);
    if (recordsToSave != null) {
      medicalRecordRepository.delete(recordsToSave);
      return true;
    }
    return false;
  }

  @Override
  public Page<MedicalRecord> searchRecords(SearchCriteria searchCriteria) {
    String patient = searchCriteria.getFullTextField1();
    if (StringUtils.isBlank(patient)) {
      return medicalRecordRepository.findAll(searchCriteria);
    }
//    TextCriteria patientCriteria = TextCriteria.forDefaultLanguage().matchingPhrase(patient);
    Page<MedicalRecord> searchedRecords = medicalRecordRepository
        .findByPatientFirstNameContainsIgnoreCaseOrPatientLastNameContainsIgnoreCaseOrPatientMiddleNameContainsIgnoreCase
            (patient, patient, patient,
                searchCriteria);
//    Page<MedicalRecord> searchedRecords = medicalRecordRepository.findAllBy(patientCriteria, searchCriteria);
    return searchedRecords;
  }
}
