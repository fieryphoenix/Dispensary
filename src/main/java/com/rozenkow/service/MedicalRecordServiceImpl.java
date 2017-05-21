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
}
