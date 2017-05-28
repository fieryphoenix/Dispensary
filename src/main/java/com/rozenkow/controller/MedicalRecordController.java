package com.rozenkow.controller;

import com.rozenkow.model.Disease;
import com.rozenkow.model.MedicalRecord;
import com.rozenkow.model.Sex;
import com.rozenkow.service.GeoService;
import com.rozenkow.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Poul Rozenkow.
 */
@Controller
public class MedicalRecordController {
  private static final String MEDICAL_RECORDS = "med_record/view.medical.records";
  private static final String EDIT_MEDICAL_RECORD = "med_record/edit.medical.record";

  private final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

  private final MedicalRecordService medicalRecordService;
  private final GeoService geoService;
  private final MessageSource messageSource;

  @InitBinder
  public void bindingPreparation(WebDataBinder binder) {
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
    CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
    binder.registerCustomEditor(Date.class, dateEditor);
  }

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
    return MEDICAL_RECORDS;
  }

  @RequestMapping(path = {"/medrecord/{id}", "/medrecord"}, method = RequestMethod.GET)
  public String loadOrCreateMedicalRecord(@PathVariable("id") Optional<String> id, Model model) {
    logger.debug("loadOrCreateMedicalRecord(): id = {}", id);
    MedicalRecord medicalRecord = id.map(medicalRecordService::getRecord).orElseGet(MedicalRecord::new);
    initRecordForEdit(model, medicalRecord);

    return EDIT_MEDICAL_RECORD;
  }

  private void initRecordForEdit(Model model, MedicalRecord medicalRecord) {
    model.addAttribute("MedRecord", medicalRecord);
    model.addAttribute("Sexes", getSexMap());
    model.addAttribute("Countries", geoService.getLocalizedCountries());
  }

  @RequestMapping(path = {"/medrecord"}, method = RequestMethod.POST)
  public String saveMedicalRecord(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, BindingResult result,
                                  RedirectAttributes redirectAttributes, Model model) {
    logger.debug("saveMedicalRecord(): medicalRecord = {}", medicalRecord);

    if (!result.hasErrors()) {
      medicalRecordService.saveRecord(medicalRecord);

      redirectAttributes.addFlashAttribute("css", "success");
      redirectAttributes.addFlashAttribute("msgKey", "Success.saved");
      return "redirect:/medrecord/" + medicalRecord.getId();
    } else {
      return EDIT_MEDICAL_RECORD;
    }
  }

  @RequestMapping(path = {"/medrecord/{id}/delete"}, method = RequestMethod.POST)
  public String deleteMedicalRecord(@PathVariable("id") String id, Model model) {
    logger.debug("deleteMedicalRecord(): id = {}", id);
    boolean removed = medicalRecordService.removeRecord(id);
    if (removed) {
      model.addAttribute("css", "info");
      model.addAttribute("msgKey", "Success.removed");
    }

    return showMedicalRecords(model);
  }

  @RequestMapping(path = {"/medrecord/addPhone"}, method = RequestMethod.POST)
  public String addPatientPhone(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, Model model) {
    logger.debug("addPatientPhone(): MedRecord = {}", medicalRecord);

    medicalRecord.getPatient().getPhones().add("");
    initRecordForEdit(model, medicalRecord);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/deletePhone/{index}"}, method = RequestMethod.POST)
  public String deletePatientPhone(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @PathVariable("index") int
      phoneIndex, Model model) {
    logger.debug("deletePatientPhone(): phoneIndex={}, MedRecord = {}", phoneIndex, medicalRecord);
    List<String> phones = medicalRecord.getPatient().getPhones();
    if (phones.size() > phoneIndex) {
      phones.remove(phoneIndex);
    }
    initRecordForEdit(model, medicalRecord);

    return EDIT_MEDICAL_RECORD;
  }


  @RequestMapping(path = {"/medrecord/addDisease"}, method = RequestMethod.POST)
  public String addDisease(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, Model model) {
    logger.debug("addDisease(): MedRecord = {}", medicalRecord);

    medicalRecord.getDiseases().add(new Disease());
    initRecordForEdit(model, medicalRecord);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/deleteDisease/{index}"}, method = RequestMethod.POST)
  public String deleteDisease(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @PathVariable("index") int
      index, Model model) {
    logger.debug("deleteDisease(): index={}, MedRecord = {}", index, medicalRecord);
    List<Disease> diseases = medicalRecord.getDiseases();
    if (diseases.size() > index) {
      diseases.remove(index);
    }
    initRecordForEdit(model, medicalRecord);

    return EDIT_MEDICAL_RECORD;
  }

  private Map<String, String> getSexMap() {
    Map<String, String> sexes = new LinkedHashMap<>();
    sexes.put(Sex.MALE.name(), messageSource.getMessage("page.field.sex.male", null, LocaleContextHolder.getLocale()));
    sexes.put(Sex.FEMALE.name(), messageSource.getMessage("page.field.sex.female", null, LocaleContextHolder
        .getLocale()));

    return sexes;
  }
}
