package com.rozenkow.controller;

import com.rozenkow.model.Role;
import com.rozenkow.model.Sex;
import com.rozenkow.model.Speciality;
import com.rozenkow.model.User;
import com.rozenkow.model.ui.SearchCriteria;
import com.rozenkow.service.DictionaryService;
import com.rozenkow.service.GeoService;
import com.rozenkow.service.UserService;
import com.rozenkow.validator.LoginFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Controller
@SessionAttributes("UserForm")
public class UserController {
  private static final String LOGIN_PAGE = "login";
  private static final String USERS = "users/view.users";
  private static final String EDIT_USER = "users/edit.user";

  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  private final UserService userService;
  private final DictionaryService dictionaryService;
  private final GeoService geoService;
  private final LoginFormValidator loginFormValidator;

  @Autowired
  public UserController(UserService userService, DictionaryService dictionaryService, GeoService geoService,
                        LoginFormValidator
                            loginFormValidator) {
    this.userService = userService;
    this.dictionaryService = dictionaryService;
    this.geoService = geoService;
    this.loginFormValidator = loginFormValidator;
  }

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
//    binder.setValidator(loginFormValidator);
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
    binder.registerCustomEditor(Date.class, dateEditor);
  }

  @RequestMapping(path = "/login", method = RequestMethod.GET)
  public String login(Model model) {
    if (!model.containsAttribute("UserForm")) {
      model.addAttribute("UserForm", new User());
    }
    model.addAttribute("Remember", false);
    return LOGIN_PAGE;
  }

  @RequestMapping(path = "/logout", method = RequestMethod.GET)
  public String doLogout(Model model) {
    model.asMap().clear();
    SecurityContextHolder.getContext().setAuthentication(null);
    return "/";
  }

  @RequestMapping(path = "/users", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ROLE_OPERATOR')")
  public String showUsers(@ModelAttribute("SearchCriteria") SearchCriteria searchCriteria, Model model) {
    List<User> records = userService.searchRecords(searchCriteria);
    model.addAttribute("Users", records);
    model.addAttribute("SearchCriteria", searchCriteria);
    return USERS;
  }

  @RequestMapping(path = "/users", method = RequestMethod.POST)
  @PreAuthorize("hasRole('ROLE_OPERATOR')")
  public String searchUsers(@ModelAttribute("SearchCriteria") SearchCriteria searchCriteria, Model model) {
    List<User> users = userService.searchRecords(searchCriteria);
    model.addAttribute("Users", users);
    model.addAttribute("SearchCriteria", searchCriteria);
    return USERS;
  }

  @RequestMapping(path = {"/user/load/{id}/{viewMode}", "/user"}, method = RequestMethod.GET)
  @PreAuthorize("hasRole('OPERATOR')")
  public String loadOrCreateUser(@PathVariable(name = "id", required = false) Optional<String> id,
                                 @PathVariable(name = "viewMode", required = false) String
                                     viewMode, Model model) {
    logger.debug("loadOrCreateUser(): id = {}, viewMode = {}", id, viewMode);
    User user = id.map(userService::getById).orElseGet(User::new);
    initRecordForEdit(model, user, viewMode);

    return EDIT_USER;
  }

  @RequestMapping(path = {"/user"}, method = RequestMethod.POST)
  @PreAuthorize("hasRole('OPERATOR')")
  public String saveUser(@ModelAttribute("User") User user, BindingResult result,
                         RedirectAttributes redirectAttributes, Model model) {
    logger.debug("saveUser(): medicalRecord = {}", user);

    //todo verify form

    if (!result.hasErrors()) {
      try {
        user = userService.saveUser(user);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msgKey", "Success.saved");
        return "redirect:/user/load/" + user.getId() + "/edit";
      } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
        logger.error("save user", e);
      }
    }
    initRecordForEdit(model, user, "false");
    return EDIT_USER;
  }

  @RequestMapping(path = {"/user/{id}/delete"}, method = RequestMethod.POST)
  @PreAuthorize("hasRole('ADMIN')")
  public String deleteUser(@PathVariable("id") String id, Model model) {
    logger.debug("deleteUser(): id = {}", id);
    boolean removed = userService.removeUser(id);
    if (removed) {
      model.addAttribute("css", "success");
      model.addAttribute("msgKey", "Success.removed");
    }

    return showUsers(new SearchCriteria(), model);
  }

  private void initRecordForEdit(Model model, User user, String readOnly) {
    final Map<String, String> sexesMap = dictionaryService.buildLocalizedMap("page.field.sex.", Sex.class, true);
    final Map<String, String> rolesMap = dictionaryService.buildLocalizedMap("page.field.role.", Role.class, false);
    Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
        .getAuthorities();
    Set<String> authRoles = authorities.stream().map(GrantedAuthority::getAuthority).collect(toSet());
    for (Role role : Role.values()) {
      if (!authRoles.containsAll(Arrays.asList(role.getGrantedRoles()))) {
        rolesMap.remove(role.name());
      }
    }

    final Map<String, String> specialitiesMap = dictionaryService.buildLocalizedMap("page.field.speciality.",
        Speciality.class, true);
    model.addAttribute("User", user);
    model.addAttribute("Sexes", sexesMap);
    model.addAttribute("Countries", geoService.getLocalizedCountries());
    model.addAttribute("Roles", rolesMap);
    model.addAttribute("Specialities", specialitiesMap);
    model.addAttribute("readOnlyForm", "view".equalsIgnoreCase(readOnly) || "true".equalsIgnoreCase(readOnly));
  }
}
