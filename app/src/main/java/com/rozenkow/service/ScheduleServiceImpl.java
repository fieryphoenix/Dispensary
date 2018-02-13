package com.rozenkow.service;

import com.rozenkow.db.ScheduleRepository;
import com.rozenkow.db.UserRepository;
import com.rozenkow.model.MedicalRecord;
import com.rozenkow.model.User;
import com.rozenkow.model.ui.CurrentVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Poul Rozenkow.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
  private final ScheduleRepository scheduleRepository;
  private final UserRepository userRepository;

  @Autowired
  public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository) {
    this.scheduleRepository = scheduleRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<CurrentVisit> getVisitsForToday(String username) {
    User user = userRepository.findDistinctFirstByUsernameIgnoreCase(username);

    LocalDate today = LocalDate.now();
    LocalDateTime from = today.atStartOfDay();
    LocalDateTime to = today.atTime(23, 59);
    List<MedicalRecord> medicalRecords = scheduleRepository.findDistinctByVisitsVisitToIDAndVisitsFromBetween(user
            .getId(),
        from, to);

    List<CurrentVisit> allCurrentVisits = new ArrayList<>();
    for (MedicalRecord medicalRecord : medicalRecords) {
      List<CurrentVisit> currentVisits = medicalRecord.getVisits().stream()
          .filter(visit -> visit.getFrom().isAfter(from))
          .filter(visit -> visit.getTo().isBefore(to))
          .map(visit -> new CurrentVisit(visit, medicalRecord))
          .collect(Collectors.toList());

      allCurrentVisits.addAll(currentVisits);
    }
    Comparator<CurrentVisit> comparator = Comparator.comparing(v -> v.getVisit().getFrom());
    allCurrentVisits.sort(comparator);

    return allCurrentVisits;
  }
}
