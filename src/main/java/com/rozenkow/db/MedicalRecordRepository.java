package com.rozenkow.db;

import com.rozenkow.model.MedicalRecord;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
  List<MedicalRecord> findByPatientFirstNameOrPatientLastNameOrPatientMiddleName(String firstName, String lastName,
                                                                                 String middleName, TextCriteria
                                                                                     patientCriteria);
}
