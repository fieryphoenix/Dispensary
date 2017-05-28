package com.rozenkow.model.ui;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by Poul Rozenkow.
 */
public class SearchCriteria implements Serializable, Pageable {
  private String patient;
  private String order = "asc";
  private Integer currentPage = 1;
  private Integer totalPages = 1;
  private boolean hasNext = false;
  private boolean hasPrev = false;

  public String getPatient() {
    return patient;
  }

  public void setPatient(String patient) {
    this.patient = patient;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }

  public boolean isHasPrev() {
    return hasPrev;
  }

  public void setHasPrev(boolean hasPrev) {
    this.hasPrev = hasPrev;
  }

  @Override
  public int getPageNumber() {
    return 0;
  }

  @Override
  public int getPageSize() {
    return 0;
  }

  @Override
  public int getOffset() {
    return 0;
  }

  @Override
  public Sort getSort() {
    return null;
  }

  @Override
  public Pageable next() {
    return null;
  }

  @Override
  public Pageable previousOrFirst() {
    return null;
  }

  @Override
  public Pageable first() {
    return null;
  }

  @Override
  public boolean hasPrevious() {
    return false;
  }

  @Override
  public String toString() {
    return "SearchCriteria{" +
           "patient='" + patient + '\'' +
           ", order='" + order + '\'' +
           ", currentPage=" + currentPage +
           ", totalPages=" + totalPages +
           ", hasNext=" + hasNext +
           ", hasPrev=" + hasPrev +
           '}';
  }
}
