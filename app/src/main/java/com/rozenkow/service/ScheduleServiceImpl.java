package com.rozenkow.service;

import com.rozenkow.db.ScheduleRepository;
import com.rozenkow.model.MedicalRecord;
import com.rozenkow.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Poul Rozenkow.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
  private final ScheduleRepository scheduleRepository;

  @Autowired
  public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  @Override
  public List<Visit> getVisitsForToday() {

    LocalDate today = LocalDate.now();
    LocalDateTime from = today.atStartOfDay();
    LocalDateTime to = today.atTime(23, 59);

    List<Visit> visits = scheduleRepository.findDistinctByVisitsFromBetween(from, to)
        .stream()
        .map(MedicalRecord::getVisits)
        .flatMap(Collection::stream)
        .filter(visit -> visit.getFrom().isAfter(from))
        .filter(visit -> visit.getTo().isBefore(to))
        .collect(Collectors.toList());
    return visits;
  }
}
