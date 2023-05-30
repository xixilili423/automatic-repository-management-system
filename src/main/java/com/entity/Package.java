package com.entity;


public class Package {

  private long packageid;
  private String shippername;
  private String shippercontact;
  private String shipperaddress;
  private String consigneename;
  private String consigneecontact;
  private String consigneeaddress;


  public long getPackageid() {
    return packageid;
  }

  public void setPackageid(long packageid) {
    this.packageid = packageid;
  }


  public String getShippername() {
    return shippername;
  }

  public void setShippername(String shippername) {
    this.shippername = shippername;
  }


  public String getShippercontact() {
    return shippercontact;
  }

  public void setShippercontact(String shippercontact) {
    this.shippercontact = shippercontact;
  }


  public String getShipperaddress() {
    return shipperaddress;
  }

  public void setShipperaddress(String shipperaddress) {
    this.shipperaddress = shipperaddress;
  }


  public String getConsigneename() {
    return consigneename;
  }

  public void setConsigneename(String consigneename) {
    this.consigneename = consigneename;
  }


  public String getConsigneecontact() {
    return consigneecontact;
  }

  public void setConsigneecontact(String consigneecontact) {
    this.consigneecontact = consigneecontact;
  }


  public String getConsigneeaddress() {
    return consigneeaddress;
  }

  public void setConsigneeaddress(String consigneeaddress) {
    this.consigneeaddress = consigneeaddress;
  }

}
