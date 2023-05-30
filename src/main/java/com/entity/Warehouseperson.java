package com.entity;


public class Warehouseperson {

  private long warehousepersonid;
  private String username;
  private long customerid;
  private String name;
  private String contactnumber;
  private String address;
  private String email;
  private String remark;


  public long getWarehousepersonid() {
    return warehousepersonid;
  }

  public void setWarehousepersonid(long warehousepersonid) {
    this.warehousepersonid = warehousepersonid;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public long getCustomerid() {
    return customerid;
  }

  public void setCustomerid(long customerid) {
    this.customerid = customerid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

}
