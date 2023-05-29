package com.entity;


public class Shelf {

  private String shelfId;
  private long areaId;
  private long capacity;
  private long remainingCapacity;


  public String getShelfId() {
    return shelfId;
  }

  public void setShelfId(String shelfId) {
    this.shelfId = shelfId;
  }


  public long getAreaId() {
    return areaId;
  }

  public void setAreaId(long areaId) {
    this.areaId = areaId;
  }


  public long getCapacity() {
    return capacity;
  }

  public void setCapacity(long capacity) {
    this.capacity = capacity;
  }


  public long getRemainingCapacity() {
    return remainingCapacity;
  }

  public void setRemainingCapacity(long remainingCapacity) {
    this.remainingCapacity = remainingCapacity;
  }

}
