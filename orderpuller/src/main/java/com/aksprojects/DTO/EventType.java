package com.aksprojects.DTO;

public enum EventType {
  NOTIFICATION("notification-type");

  private String event;

  EventType(String event) {
    this.event = event;
  }

  public String getEventType(){
    return this.event;
  }

}
