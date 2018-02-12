package com.rozenkow.controller;

import com.rozenkow.model.Visit;
import com.rozenkow.model.VisitStatus;
import com.rozenkow.service.DictionaryService;
import com.rozenkow.service.ScheduleService;
import com.rozenkow.util.LocalDateTimeEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Poul Rozenkow.
 */
@Controller
public class WelcomeController {
  private final ScheduleService scheduleService;
  private final DictionaryService dictionaryService;

  @Autowired
  public WelcomeController(ScheduleService scheduleService, DictionaryService dictionaryService) {
    this.scheduleService = scheduleService;
    this.dictionaryService = dictionaryService;
  }

  @InitBinder
  public void bindingPreparation(WebDataBinder binder) {
    Locale locale = LocaleContextHolder.getLocale();

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
    binder.registerCustomEditor(Date.class, dateEditor);

    LocalDateTimeEditor dateTimeEditor = new LocalDateTimeEditor(locale);
    binder.registerCustomEditor(LocalDateTime.class, dateTimeEditor);
  }

  @RequestMapping("/")
  public String welcome() {
    return "redirect:/index";
  }

  @RequestMapping(path = "/index", method = RequestMethod.GET)
  public String index(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      List<Visit> visitsForToday = scheduleService.getVisitsForToday();
      final Map<String, String> visitStatusMap = dictionaryService.buildLocalizedMap("page.field.visit.status.",
          VisitStatus.class, false);

      model.addAttribute("Visits", visitsForToday);
      model.addAttribute("VisitStatuses", visitStatusMap);
      return "schedule/current_visits";
    }
    return "index";
  }
}
