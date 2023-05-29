package com.entity;


public class Area {

  private long areaId;
  private long warehouseId;
  private long shelfCount;
  private String areaName;


  public long getAreaId() {
    return areaId;
  }

  public void setAreaId(long areaId) {
    this.areaId = areaId;
  }


  public long getWarehouseId() {
    return warehouseId;
  }

  public void setWarehouseId(long warehouseId) {
    this.warehouseId = warehouseId;
  }


  public long getShelfCount() {
    return shelfCount;
  }

  public void setShelfCount(long shelfCount) {
    this.shelfCount = shelfCount;
  }


  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

}
