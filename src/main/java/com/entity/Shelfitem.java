package com.entity;


public class Shelfitem {

  private long packageid;
  private String shelfid;
  private long locationid;


  public long getPackageid() {
    return packageid;
  }

  public void setPackageid(long packageid) {
    this.packageid = packageid;
  }


  public String getShelfid() {
    return shelfid;
  }

  public void setShelfid(String shelfid) {
    this.shelfid = shelfid;
  }


  public long getLocationid() {
    return locationid;
  }

  public void setLocationid(long locationid) {
    this.locationid = locationid;
  }

}
