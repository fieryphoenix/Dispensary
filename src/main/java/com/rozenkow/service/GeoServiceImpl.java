package com.rozenkow.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Poul Rozenkow.
 */
@Service
public class GeoServiceImpl implements GeoService {
  @Override
  public Map<String, String> getLocalizedCountries() {
    Map<String, String> countries = new LinkedHashMap<>();
    Locale displayLocale = LocaleContextHolder.getLocale();
    for (String countryCode : Locale.getISOCountries()) {
      Locale locale = new Locale("", countryCode);

      countries.put(locale.getCountry(), locale.getDisplayCountry(displayLocale));
    }
    return countries;
  }
}
