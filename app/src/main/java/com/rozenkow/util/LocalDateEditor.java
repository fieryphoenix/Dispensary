package com.rozenkow.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Poul Rozenkow.
 */
public class LocalDateEditor extends PropertyEditorSupport {

  private DateTimeFormatter dateTimeFormatter;

  public LocalDateEditor(Locale locale) {
    dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
    dateTimeFormatter = dateTimeFormatter.withLocale(locale);
  }

  /**
   * Format the Date as String, using the specified DateFormat.
   */
  @Override
  public String getAsText() {
    Date value = (Date) getValue();
    if (value != null) {
      LocalDate localDate = LocalDate.from(Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.systemDefault())
          .toLocalDate());

      return this.dateTimeFormatter.format(localDate);
    } else {
      return "";
    }
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
        LocalDate localDate = LocalDate.parse(text, dateTimeFormatter);
        Date value = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        setValue(value);
      } catch (DateTimeParseException ex) {
        throw new IllegalArgumentException("Could not parse local date-time: " + ex.getMessage(), ex);
      }
    }
  }
}
