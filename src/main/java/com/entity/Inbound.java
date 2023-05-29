package com.entity;


public class Inbound {

  private long inboundId;
  private String orderId;
  private long warehousePersonId;
  private String status;
  private java.sql.Timestamp inboundTime;


  public long getInboundId() {
    return inboundId;
  }

  public void setInboundId(long inboundId) {
    this.inboundId = inboundId;
  }


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public long getWarehousePersonId() {
    return warehousePersonId;
  }

  public void setWarehousePersonId(long warehousePersonId) {
    this.warehousePersonId = warehousePersonId;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getInboundTime() {
    return inboundTime;
  }

  public void setInboundTime(java.sql.Timestamp inboundTime) {
    this.inboundTime = inboundTime;
  }

}
