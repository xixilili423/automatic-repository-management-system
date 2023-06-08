package com.entity;


import javax.persistence.Id;

public class Warehouse {
  @Id
  private long warehouseid;
  private long areacount;


  public long getWarehouseid() {
    return warehouseid;
  }

  public void setWarehouseid(long warehouseid) {
    this.warehouseid = warehouseid;
  }


  public long getAreacount() {
    return areacount;
  }

  public void setAreacount(long areacount) {
    this.areacount = areacount;
  }

}
