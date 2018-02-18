package com.rozenkow.model;

/**
 * Created by Poul Rozenkow.
 */
public class Ultrasound extends Analysis {
  private UltrasoundType type;

  public UltrasoundType getType() {
    return type;
  }

  public void setType(UltrasoundType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Ultrasound{" +
           "type=" + type +
           "} " + super.toString();
  }
}
