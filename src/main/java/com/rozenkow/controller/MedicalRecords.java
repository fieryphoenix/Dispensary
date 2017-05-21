package com.rozenkow.controller;

import com.rozenkow.model.MedicalRecord;
import com.rozenkow.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * Created by Poul Rozenkow.
 */
@Controller
public class MedicalRecords {
  private final Logger logger = LoggerFactory.getLogger(MedicalRecords.class);

  private final MedicalRecordService medicalRecordService;

  @Autowired
  public MedicalRecords(MedicalRecordService medicalRecordService) {
    this.medicalRecordService = medicalRecordService;
  }

  @RequestMapping(path = "/medrecords", method = RequestMethod.GET)
  public String showMedicalRecords(Model model) {
    model.addAttribute("MedRecords", medicalRecordService.getRecords());
    return "medical.records";
  }

  @RequestMapping(path = {"/medrecord/{id}", "/medrecord"}, method = RequestMethod.GET)
  public String newMedicalRecords(@PathVariable("id") Optional<String> id, Model model) {
    MedicalRecord medicalRecord = id.map(medicalRecordService::getRecord).orElseGet(MedicalRecord::new);
    model.addAttribute("MedRecord", medicalRecord);
    return "edit.medical.record";
  }
}
