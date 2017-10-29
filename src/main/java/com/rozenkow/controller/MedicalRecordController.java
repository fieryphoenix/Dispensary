package com.rozenkow.controller;

import com.rozenkow.model.Disease;
import com.rozenkow.model.MedicalRecord;
import com.rozenkow.model.Sex;
import com.rozenkow.model.Speciality;
import com.rozenkow.model.Ultrasound;
import com.rozenkow.model.UltrasoundType;
import com.rozenkow.model.Visit;
import com.rozenkow.model.VisitStatus;
import com.rozenkow.model.Worker;
import com.rozenkow.model.ui.SearchCriteria;
import com.rozenkow.service.DictionaryService;
import com.rozenkow.service.GeoService;
import com.rozenkow.service.MedicalRecordService;
import com.rozenkow.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
  private final DictionaryService dictionaryService;
  private final WorkerService workerService;

  @Autowired
  public MedicalRecordController(MedicalRecordService medicalRecordService, GeoService geoService, DictionaryService
      dictionaryService, WorkerService workerService) {
    this.medicalRecordService = medicalRecordService;
    this.geoService = geoService;
    this.dictionaryService = dictionaryService;
    this.workerService = workerService;
  }

  @InitBinder
  public void bindingPreparation(WebDataBinder binder) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
    binder.registerCustomEditor(Date.class, dateEditor);
  }

  @RequestMapping(path = "/medrecords", method = RequestMethod.GET)
  public String showMedicalRecords(Model model) {
    List<MedicalRecord> records = medicalRecordService.getRecords();
    model.addAttribute("MedRecords", records);
    model.addAttribute("SearchCriteria", new SearchCriteria("created"));
    return MEDICAL_RECORDS;
  }

  @RequestMapping(path = "/medrecords", method = RequestMethod.POST)
  public String searchMedicalRecords(@ModelAttribute("SearchCriteria") SearchCriteria searchCriteria, Model model) {
    Page<MedicalRecord> medicalRecords = medicalRecordService.searchRecords(searchCriteria);
    model.addAttribute("MedRecords", medicalRecords.getContent());
    model.addAttribute("SearchCriteria", searchCriteria);
    return MEDICAL_RECORDS;
  }

  @RequestMapping(path = {"/medrecord/load/{id}/{viewMode}", "/medrecord"}, method = RequestMethod.GET)
  public String loadOrCreateMedicalRecord(@PathVariable(name = "id", required = false) Optional<String> id,
                                          @PathVariable(name = "viewMode", required = false) String
                                              viewMode, Model model) {
    logger.debug("loadOrCreateMedicalRecord(): id = {}, viewMode = {}", id, viewMode);
    MedicalRecord medicalRecord = id.map(medicalRecordService::getRecord).orElseGet(MedicalRecord::new);
    initRecordForEdit(model, medicalRecord, viewMode);

    return EDIT_MEDICAL_RECORD;
  }

  private void initRecordForEdit(Model model, MedicalRecord medicalRecord, String readOnly) {
    final Map<String, String> sexesMap = dictionaryService.buildLocalizedMap("page.field.sex.", Sex.class, false);
    final Map<String, String> ultrasoundTypesMap = dictionaryService.buildLocalizedMap("page.field.ultrasound.type.",
        UltrasoundType.class, false);
    final Map<String, String> visitStatusMap = dictionaryService.buildLocalizedMap("page.field.visit.status.",
        VisitStatus.class, false);
    final Map<String, String> specialitiesMap = dictionaryService.buildLocalizedMap("page.field.speciality.",
        Speciality.class, true);
    final Map<String, String> doctorsWithSpeciality = workerService.getAvailableDoctors().stream()
        .collect(Collectors.toMap(Worker::getId, w -> specialitiesMap.get(w.getSpeciality().name()) + " " + w
            .getFullName()));

    model.addAttribute("MedRecord", medicalRecord);
    model.addAttribute("Sexes", sexesMap);
    model.addAttribute("Countries", geoService.getLocalizedCountries());
    model.addAttribute("Ultrasounds", ultrasoundTypesMap);
    model.addAttribute("VisitStatuses", visitStatusMap);
    model.addAttribute("Doctors", doctorsWithSpeciality);

    model.addAttribute("readOnlyForm", "view".equalsIgnoreCase(readOnly) || "true".equalsIgnoreCase(readOnly));
  }

  @RequestMapping(path = {"/medrecord"}, method = RequestMethod.POST)
  public String saveMedicalRecord(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, BindingResult result,
                                  RedirectAttributes redirectAttributes, Model model) {
    logger.debug("saveMedicalRecord(): medicalRecord = {}", medicalRecord);

    if (!result.hasErrors()) {
      medicalRecord = medicalRecordService.saveRecord(medicalRecord);

      redirectAttributes.addFlashAttribute("css", "success");
      redirectAttributes.addFlashAttribute("msgKey", "Success.saved");
      return "redirect:/medrecord/load/" + medicalRecord.getId() + "/edit";
    } else {
      return EDIT_MEDICAL_RECORD;
    }
  }

  @RequestMapping(path = {"/medrecord/{id}/delete"}, method = RequestMethod.POST)
  public String deleteMedicalRecord(@PathVariable("id") String id, Model model) {
    logger.debug("deleteMedicalRecord(): id = {}", id);
    boolean removed = medicalRecordService.removeRecord(id);
    if (removed) {
      model.addAttribute("css", "success");
      model.addAttribute("msgKey", "Success.removed");
    }

    return showMedicalRecords(model);
  }

  @RequestMapping(path = {"/medrecord/addPhone"}, method = RequestMethod.POST)
  public String addPatientPhone(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @ModelAttribute
      ("readOnlyForm") String readOnly, Model model) {
    logger.debug("addPatientPhone(): MedRecord = {}", medicalRecord);

    medicalRecord.getPatient().getPhones().add("");
    initRecordForEdit(model, medicalRecord, readOnly);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/deletePhone/{index}"}, method = RequestMethod.POST)
  public String deletePatientPhone(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @ModelAttribute
      ("readOnlyForm") String readOnly, @PathVariable("index") int
                                       phoneIndex, Model model) {
    logger.debug("deletePatientPhone(): phoneIndex={}, MedRecord = {}", phoneIndex, medicalRecord);
    List<String> phones = medicalRecord.getPatient().getPhones();
    if (phones.size() > phoneIndex) {
      phones.remove(phoneIndex);
    }
    initRecordForEdit(model, medicalRecord, readOnly);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/addDisease"}, method = RequestMethod.POST)
  public String addDisease(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @ModelAttribute("readOnlyForm")
      String readOnly, Model model) {
    logger.debug("addDisease(): MedRecord = {}", medicalRecord);

    medicalRecord.getDiseases().add(new Disease());
    initRecordForEdit(model, medicalRecord, readOnly);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/deleteDisease/{index}"}, method = RequestMethod.POST)
  public String deleteDisease(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @PathVariable("index") int
      index, @ModelAttribute("readOnlyForm") String readOnly, Model model) {
    logger.debug("deleteDisease(): index={}, MedRecord = {}", index, medicalRecord);
    List<Disease> diseases = medicalRecord.getDiseases();
    if (diseases.size() > index) {
      diseases.remove(index);
    }
    initRecordForEdit(model, medicalRecord, readOnly);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/addUltrasound"}, method = RequestMethod.POST)
  public String addUltrasound(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @ModelAttribute
      ("readOnlyForm") String readOnly, Model model) {
    logger.debug("addUltrasound(): MedRecord = {}", medicalRecord);

    medicalRecord.getUltrasounds().add(new Ultrasound());
    initRecordForEdit(model, medicalRecord, readOnly);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/deleteUltrasound/{index}"}, method = RequestMethod.POST)
  public String deleteUltrasound(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @PathVariable("index") int
      index, @ModelAttribute("readOnlyForm") String readOnly, Model model) {
    logger.debug("deleteUltrasound(): index={}, MedRecord = {}", index, medicalRecord);
    List<Ultrasound> ultrasounds = medicalRecord.getUltrasounds();
    if (ultrasounds.size() > index) {
      ultrasounds.remove(index);
    }
    initRecordForEdit(model, medicalRecord, readOnly);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/addVisit"}, method = RequestMethod.POST)
  public String addVisit(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @ModelAttribute
      ("readOnlyForm") String readOnly, Model model) {
    logger.debug("addVisit(): MedRecord = {}", medicalRecord);

    medicalRecord.getVisits().add(new Visit());
    initRecordForEdit(model, medicalRecord, readOnly);

    return EDIT_MEDICAL_RECORD;
  }

  @RequestMapping(path = {"/medrecord/deleteVisit/{index}"}, method = RequestMethod.POST)
  public String deleteVisit(@ModelAttribute("MedRecord") MedicalRecord medicalRecord, @PathVariable("index") int
      index, @ModelAttribute("readOnlyForm") String readOnly, Model model) {
    logger.debug("deleteVisit(): index={}, MedRecord = {}", index, medicalRecord);
    List<Visit> visits = medicalRecord.getVisits();
    if (visits.size() > index) {
      visits.remove(index);
    }
    initRecordForEdit(model, medicalRecord, readOnly);

    return EDIT_MEDICAL_RECORD;
  }
}
