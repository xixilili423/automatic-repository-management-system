package com.entity;


public class Transactionrecord {

  private long transactionid;
  private long customerid;
  private String username;
  private double transactionamount;
  private java.sql.Timestamp transactiontime;
  private String transactionremark;


  public long getTransactionid() {
    return transactionid;
  }

  public void setTransactionid(long transactionid) {
    this.transactionid = transactionid;
  }


  public long getCustomerid() {
    return customerid;
  }

  public void setCustomerid(long customerid) {
    this.customerid = customerid;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public double getTransactionamount() {
    return transactionamount;
  }

  public void setTransactionamount(double transactionamount) {
    this.transactionamount = transactionamount;
  }


  public java.sql.Timestamp getTransactiontime() {
    return transactiontime;
  }

  public void setTransactiontime(java.sql.Timestamp transactiontime) {
    this.transactiontime = transactiontime;
  }


  public String getTransactionremark() {
    return transactionremark;
  }

  public void setTransactionremark(String transactionremark) {
    this.transactionremark = transactionremark;
  }

}
