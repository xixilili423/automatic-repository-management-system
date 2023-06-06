package com.entity;


public class Inbound {

  private long inboundid;
  private String orderid;
  private long warehousepersonid;
  private String status;
  private java.sql.Timestamp inboundtime;
  private String userid;
  private String managerid;


  public long getInboundid() {
    return inboundid;
  }

  public void setInboundid(long inboundid) {
    this.inboundid = inboundid;
  }


  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }


  public long getWarehousepersonid() {
    return warehousepersonid;
  }

  public void setWarehousepersonid(long warehousepersonid) {
    this.warehousepersonid = warehousepersonid;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getInboundtime() {
    return inboundtime;
  }

  public void setInboundtime(java.sql.Timestamp inboundtime) {
    this.inboundtime = inboundtime;
  }


  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }


  public String getManagerid() {
    return managerid;
  }

  public void setManagerid(String managerid) {
    this.managerid = managerid;
  }

}
