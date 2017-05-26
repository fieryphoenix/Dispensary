package com.rozenkow.service;

import com.rozenkow.db.MedicalRecordRepository;
import com.rozenkow.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
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
}
