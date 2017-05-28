package com.rozenkow.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Poul Rozenkow.
 */
public class MapUtils {
  public static LinkedHashMap<String, String> sortMapByValue(Map<String, String> mapToSort) {
    return mapToSort.entrySet().stream()
        .sorted((o1, o2) -> o1.getValue().compareToIgnoreCase(o2.getValue()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (u, u2) -> u, LinkedHashMap::new));
  }
}
