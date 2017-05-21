package com.rozenkow.service;

import com.rozenkow.model.MedicalRecord;

import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
public interface MedicalRecordService {
  List<MedicalRecord> getRecords();

  MedicalRecord getRecord(String id);
}
