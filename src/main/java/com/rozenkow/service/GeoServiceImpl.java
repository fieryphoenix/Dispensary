package com.rozenkow.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Poul Rozenkow.
 */
@Service
public class GeoServiceImpl implements GeoService {
  @Override
  public Map<String, String> getLocalizedCountries() {
    Map<String, String> countries = new HashMap<>();
    Locale displayLocale = LocaleContextHolder.getLocale();
    String[] isoCountries = Locale.getISOCountries();
    for (String countryCode : isoCountries) {
      Locale locale = new Locale("", countryCode);

      countries.put(locale.getCountry(), locale.getDisplayCountry(displayLocale));
    }
    countries = countries.entrySet().stream()
        .sorted((o1, o2) -> o1.getValue().compareToIgnoreCase(o2.getValue()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (u, u2) -> u, LinkedHashMap::new));
    return countries;
  }
}
