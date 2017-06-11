package com.rozenkow.model;

import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * Created by Poul Rozenkow.
 */
public class Visit extends AuditEntity {
  private String visitToID;
  @Transient
  private Worker visitTo;
  private Date from;
  private Date to;
  private VisitStatus status;

  public String getVisitToID() {
    return visitToID;
  }

  public void setVisitToID(String visitToID) {
    this.visitToID = visitToID;
  }

  public Worker getVisitTo() {
    return visitTo;
  }

  public void setVisitTo(Worker visitTo) {
    this.visitTo = visitTo;
  }

  public Date getFrom() {
    return from;
  }

  public void setFrom(Date from) {
    this.from = from;
  }

  public Date getTo() {
    return to;
  }

  public void setTo(Date to) {
    this.to = to;
  }

  public VisitStatus getStatus() {
    return status;
  }

  public void setStatus(VisitStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Visit{" +
           "visitToID='" + visitToID + '\'' +
           ", visitTo=" + visitTo +
           ", from=" + from +
           ", to=" + to +
           ", status=" + status +
           "} " + super.toString();
  }
}
