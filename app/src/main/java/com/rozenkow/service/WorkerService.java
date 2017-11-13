package com.rozenkow.service;

import com.rozenkow.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Poul Rozenkow.
 */
@Service
public class WorkerService {
  private final UserService userService;

  @Autowired
  public WorkerService(UserService userService) {
    this.userService = userService;
  }

  public List<Worker> getAvailableDoctors() {
    return userService.getAllWorkers();
  }
}
