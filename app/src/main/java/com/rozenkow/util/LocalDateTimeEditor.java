package com.rozenkow.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Created by Poul Rozenkow.
 */
public class LocalDateTimeEditor extends PropertyEditorSupport {

  private DateTimeFormatter dateTimeFormatter;

  public LocalDateTimeEditor(Locale locale) {
    dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm");
    dateTimeFormatter = dateTimeFormatter.withLocale(locale);
  }

  /**
   * Format the Date as String, using the specified DateFormat.
   */
  @Override
  public String getAsText() {
    LocalDateTime value = (LocalDateTime) getValue();

    return (value != null ? this.dateTimeFormatter.format(value) : "");
  }

  /**
   * Parse the Date from the given text, using the specified DateFormat.
   */
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    if (!StringUtils.hasText(text)) {
      // Treat empty String as null value.
      setValue(null);
    } else {
      try {
        setValue(LocalDateTime.parse(text, dateTimeFormatter));
      } catch (DateTimeParseException ex) {
        throw new IllegalArgumentException("Could not parse local date-time: " + ex.getMessage(), ex);
      }
    }
  }
}
