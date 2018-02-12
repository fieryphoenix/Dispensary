package com.rozenkow.service;

import com.rozenkow.model.Visit;

import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
public interface ScheduleService {
  List<Visit> getVisitsForToday();
}
