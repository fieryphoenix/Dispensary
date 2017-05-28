package com.rozenkow.model.ui;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by Poul Rozenkow.
 */
public class SearchCriteria extends PageRequest implements Serializable {
  private static final int DEFAULT_SIZE = 10;

  private String fullTextField1;

  public SearchCriteria(int page, int size) {
    super(page, size);
  }

  public SearchCriteria(int page, int size, Sort.Direction direction, String... properties) {
    super(page, size, direction, properties);
  }

  public SearchCriteria(int page, int size, Sort sort) {
    super(page, size, sort);
  }

  public SearchCriteria() {
    super(0, DEFAULT_SIZE, new Sort(Sort.DEFAULT_DIRECTION, "id"));
  }

  public SearchCriteria(String... properties) {
    super(0, DEFAULT_SIZE, new Sort(Sort.DEFAULT_DIRECTION, properties));
  }

  public String getFullTextField1() {
    return fullTextField1;
  }

  public void setFullTextField1(String fullTextField1) {
    this.fullTextField1 = fullTextField1;
  }

  @Override
  public String toString() {
    return "SearchCriteria{" +
           "fullTextField1='" + fullTextField1 + '\'' +
           "pageRequest='" + super.toString() + '\'' +
           '}';
  }
}
