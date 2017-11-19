package com.rozenkow.model;

import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;

/**
 * Created by Poul Rozenkow.
 */
public class Visit extends AuditEntity {
  private String visitToID;
  @Transient
  private Worker visitTo;
  private LocalDateTime from;
  private LocalDateTime to;
  private String goal;
  private VisitStatus status;

  public Visit() {
    status = VisitStatus.Planned;
  }

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

  public LocalDateTime getFrom() {
    return from;
  }

  public void setFrom(LocalDateTime from) {
    this.from = from;
  }

  public LocalDateTime getTo() {
    return to;
  }

  public void setTo(LocalDateTime to) {
    this.to = to;
  }

  public VisitStatus getStatus() {
    return status;
  }

  public void setStatus(VisitStatus status) {
    this.status = status;
  }

  public String getGoal() {
    return goal;
  }

  public void setGoal(String goal) {
    this.goal = goal;
  }

  @Override
  public String toString() {
    return "Visit{" +
           "visitToID='" + visitToID + '\'' +
           ", visitTo=" + visitTo +
           ", from=" + from +
           ", to=" + to +
           ", goal='" + goal + '\'' +
           ", status=" + status +
           "} " + super.toString();
  }
}
