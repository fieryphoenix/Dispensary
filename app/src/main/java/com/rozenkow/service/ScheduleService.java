package com.rozenkow.service;

import com.rozenkow.model.ui.CurrentVisit;

import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
public interface ScheduleService {
  List<CurrentVisit> getVisitsForToday(String username);
}
