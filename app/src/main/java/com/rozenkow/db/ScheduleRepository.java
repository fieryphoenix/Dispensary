package com.rozenkow.db;

import com.rozenkow.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Repository
public interface ScheduleRepository extends MongoRepository<MedicalRecord, String> {

  List<MedicalRecord> findDistinctByVisitsFromBetween(LocalDateTime from, LocalDateTime to);
}
