package com.entity;


public class Outbound {

  private long outboundid;
  private String orderid;
  private long outboundpersonid;
  private String status;
  private java.sql.Timestamp outboundtime;


  public long getOutboundid() {
    return outboundid;
  }

  public void setOutboundid(long outboundid) {
    this.outboundid = outboundid;
  }


  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }


  public long getOutboundpersonid() {
    return outboundpersonid;
  }

  public void setOutboundpersonid(long outboundpersonid) {
    this.outboundpersonid = outboundpersonid;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getOutboundtime() {
    return outboundtime;
  }

  public void setOutboundtime(java.sql.Timestamp outboundtime) {
    this.outboundtime = outboundtime;
  }

}
