package com.rozenkow.controller;

import com.rozenkow.model.MedicalRecord;
import com.rozenkow.model.Sex;
import com.rozenkow.service.GeoService;
import com.rozenkow.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Poul Rozenkow.
 */
@Controller
public class MedicalRecordController {
  private final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

  private final MedicalRecordService medicalRecordService;
  private final GeoService geoService;
  private final MessageSource messageSource;

  @Autowired
  public MedicalRecordController(MedicalRecordService medicalRecordService, GeoService geoService, MessageSource
      messageSource) {
    this.medicalRecordService = medicalRecordService;
    this.geoService = geoService;
    this.messageSource = messageSource;
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
    model.addAttribute("Sexes", getSexMap());
    model.addAttribute("Countries", geoService.getLocalizedCountries());

    LocaleContextHolder.getLocale();

    return "edit.medical.record";
  }

  private Map<String, String> getSexMap() {
    Map<String, String> sexes = new LinkedHashMap<>();
    sexes.put(Sex.MALE.name(), messageSource.getMessage("page.field.sex.male", null, LocaleContextHolder.getLocale()));
    sexes.put(Sex.FEMALE.name(), messageSource.getMessage("page.field.sex.female", null, LocaleContextHolder
        .getLocale()));

    return sexes;
  }
}
