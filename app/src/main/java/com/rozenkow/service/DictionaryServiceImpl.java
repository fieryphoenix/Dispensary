package com.rozenkow.service;

import com.rozenkow.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Created by Poul Rozenkow.
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
  private final MessageSource messageSource;

  @Autowired
  public DictionaryServiceImpl(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public <T extends Enum> Map<String, String> buildLocalizedMap(String prefixKey, Class<T> enumClazz, boolean
      allowEmpty) {
    Map<String, String> enumLocalizedByKeyMap = new HashMap<>();
    Locale currentLocale = LocaleContextHolder.getLocale();
    if (allowEmpty) {
      enumLocalizedByKeyMap.put(EMPTY, EMPTY);
    }
    for (T enumConst : enumClazz.getEnumConstants()) {
      enumLocalizedByKeyMap.put(enumConst.name(), messageSource.getMessage(prefixKey + enumConst.name(), null,
          currentLocale));
    }
    enumLocalizedByKeyMap = MapUtils.sortMapByValue(enumLocalizedByKeyMap);
    return enumLocalizedByKeyMap;
  }
}
