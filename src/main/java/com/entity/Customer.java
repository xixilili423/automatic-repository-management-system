package com.entity;


public class Customer {

  private long customerId;
  private String username;
  private String companyName;
  private String contactPersonName;
  private String contactNumber;
  private String address;
  private double payableAmount;
  private String email;
  private String remark;
  private String bankName;
  private String bankCardNumber;


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


  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }


  public String getContactPersonName() {
    return contactPersonName;
  }

  public void setContactPersonName(String contactPersonName) {
    this.contactPersonName = contactPersonName;
  }


  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public double getPayableAmount() {
    return payableAmount;
  }

  public void setPayableAmount(double payableAmount) {
    this.payableAmount = payableAmount;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }


  public String getBankCardNumber() {
    return bankCardNumber;
  }

  public void setBankCardNumber(String bankCardNumber) {
    this.bankCardNumber = bankCardNumber;
  }

}
