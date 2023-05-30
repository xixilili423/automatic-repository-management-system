package com.entity;


public class Area {

  private long areaid;
  private long warehouseid;
  private long shelfcount;
  private String areaname;


  public long getAreaid() {
    return areaid;
  }

  public void setAreaid(long areaid) {
    this.areaid = areaid;
  }


  public long getWarehouseid() {
    return warehouseid;
  }

  public void setWarehouseid(long warehouseid) {
    this.warehouseid = warehouseid;
  }


  public long getShelfcount() {
    return shelfcount;
  }

  public void setShelfcount(long shelfcount) {
    this.shelfcount = shelfcount;
  }


  public String getAreaname() {
    return areaname;
  }

  public void setAreaname(String areaname) {
    this.areaname = areaname;
  }

}
