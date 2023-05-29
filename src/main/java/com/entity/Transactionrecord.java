package com.entity;


public class Transactionrecord {

  private long transactionId;
  private long customerId;
  private String username;
  private double transactionAmount;
  private java.sql.Timestamp transactionTime;
  private String transactionRemark;


  public long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(long transactionId) {
    this.transactionId = transactionId;
  }


  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public double getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(double transactionAmount) {
    this.transactionAmount = transactionAmount;
  }


  public java.sql.Timestamp getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(java.sql.Timestamp transactionTime) {
    this.transactionTime = transactionTime;
  }


  public String getTransactionRemark() {
    return transactionRemark;
  }

  public void setTransactionRemark(String transactionRemark) {
    this.transactionRemark = transactionRemark;
  }

}
