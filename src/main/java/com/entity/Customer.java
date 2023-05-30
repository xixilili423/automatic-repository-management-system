package com.entity;


public class Customer {

  private long customerid;
  private String username;
  private String companyname;
  private String contactpersonname;
  private String contactnumber;
  private String address;
  private double payableamount;
  private String email;
  private String remark;
  private String bankname;
  private String bankcardnumber;


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


  public String getCompanyname() {
    return companyname;
  }

  public void setCompanyname(String companyname) {
    this.companyname = companyname;
  }


  public String getContactpersonname() {
    return contactpersonname;
  }

  public void setContactpersonname(String contactpersonname) {
    this.contactpersonname = contactpersonname;
  }


  public String getContactnumber() {
    return contactnumber;
  }

  public void setContactnumber(String contactnumber) {
    this.contactnumber = contactnumber;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public double getPayableamount() {
    return payableamount;
  }

  public void setPayableamount(double payableamount) {
    this.payableamount = payableamount;
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


  public String getBankname() {
    return bankname;
  }

  public void setBankname(String bankname) {
    this.bankname = bankname;
  }


  public String getBankcardnumber() {
    return bankcardnumber;
  }

  public void setBankcardnumber(String bankcardnumber) {
    this.bankcardnumber = bankcardnumber;
  }

}
