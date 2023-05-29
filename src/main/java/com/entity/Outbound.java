package com.entity;


public class Outbound {

  private long outboundId;
  private String orderId;
  private long outboundPersonId;
  private String status;
  private java.sql.Timestamp outboundTime;


  public long getOutboundId() {
    return outboundId;
  }

  public void setOutboundId(long outboundId) {
    this.outboundId = outboundId;
  }


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public long getOutboundPersonId() {
    return outboundPersonId;
  }

  public void setOutboundPersonId(long outboundPersonId) {
    this.outboundPersonId = outboundPersonId;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getOutboundTime() {
    return outboundTime;
  }

  public void setOutboundTime(java.sql.Timestamp outboundTime) {
    this.outboundTime = outboundTime;
  }

}
