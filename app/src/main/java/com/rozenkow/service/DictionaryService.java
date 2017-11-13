package com.rozenkow.service;

import java.util.Map;

/**
 * Created by Poul Rozenkow.
 */
public interface DictionaryService {
  <T extends Enum> Map<String, String> buildLocalizedMap(String prefixKey, Class<T> enumClazz, boolean allowEmpty);
}
