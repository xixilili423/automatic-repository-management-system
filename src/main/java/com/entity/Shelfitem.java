package com.entity;


public class Shelfitem {

  private long packageId;
  private String shelfId;
  private long locationId;


  public long getPackageId() {
    return packageId;
  }

  public void setPackageId(long packageId) {
    this.packageId = packageId;
  }


  public String getShelfId() {
    return shelfId;
  }

  public void setShelfId(String shelfId) {
    this.shelfId = shelfId;
  }


  public long getLocationId() {
    return locationId;
  }

  public void setLocationId(long locationId) {
    this.locationId = locationId;
  }

}
