package com.entity;


public class Package {

  private long packageId;
  private String shipperName;
  private String shipperContact;
  private String shipperAddress;
  private String consigneeName;
  private String consigneeContact;
  private String consigneeAddress;


  public long getPackageId() {
    return packageId;
  }

  public void setPackageId(long packageId) {
    this.packageId = packageId;
  }


  public String getShipperName() {
    return shipperName;
  }

  public void setShipperName(String shipperName) {
    this.shipperName = shipperName;
  }


  public String getShipperContact() {
    return shipperContact;
  }

  public void setShipperContact(String shipperContact) {
    this.shipperContact = shipperContact;
  }


  public String getShipperAddress() {
    return shipperAddress;
  }

  public void setShipperAddress(String shipperAddress) {
    this.shipperAddress = shipperAddress;
  }


  public String getConsigneeName() {
    return consigneeName;
  }

  public void setConsigneeName(String consigneeName) {
    this.consigneeName = consigneeName;
  }


  public String getConsigneeContact() {
    return consigneeContact;
  }

  public void setConsigneeContact(String consigneeContact) {
    this.consigneeContact = consigneeContact;
  }


  public String getConsigneeAddress() {
    return consigneeAddress;
  }

  public void setConsigneeAddress(String consigneeAddress) {
    this.consigneeAddress = consigneeAddress;
  }

}
