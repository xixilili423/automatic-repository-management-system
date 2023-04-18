package com.entity;


public class Warehouse {

  private long id;
  private long capacityX;
  private long capacityY;
  private long gateMachine;
  private long avg;
  private String username;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getCapacityX() {
    return capacityX;
  }

  public void setCapacityX(long capacityX) {
    this.capacityX = capacityX;
  }


  public long getCapacityY() {
    return capacityY;
  }

  public void setCapacityY(long capacityY) {
    this.capacityY = capacityY;
  }


  public long getGateMachine() {
    return gateMachine;
  }

  public void setGateMachine(long gateMachine) {
    this.gateMachine = gateMachine;
  }


  public long getAvg() {
    return avg;
  }

  public void setAvg(long avg) {
    this.avg = avg;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
