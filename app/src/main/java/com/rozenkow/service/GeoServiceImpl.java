package com.rozenkow.service;

import com.rozenkow.util.MapUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
    countries = MapUtils.sortMapByValue(countries);
    return countries;
  }
}
