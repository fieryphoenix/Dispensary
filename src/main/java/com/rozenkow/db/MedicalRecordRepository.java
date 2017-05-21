package com.rozenkow.db;

import com.rozenkow.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Poul Rozenkow.
 */
@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
}
