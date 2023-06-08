package com.entity;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.persistence.Id;

public class User {
  @Id
  private String id;
  private String name;
  private String permission;
  private String password;
  private long warehouseid;
  private String contactnumber;
  private String transitstation;
  private java.sql.Timestamp accountcreatedtime;
  private String email;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public long getWarehouseid() {
    return warehouseid;
  }

  public void setWarehouseid(long warehouseid) {
    this.warehouseid = warehouseid;
  }


  public String getContactnumber() {
    return contactnumber;
  }

  public void setContactnumber(String contactnumber) {
    this.contactnumber = contactnumber;
  }


  public String getTransitstation() {
    return transitstation;
  }

  public void setTransitstation(String transitstation) {
    this.transitstation = transitstation;
  }


  public java.sql.Timestamp getAccountcreatedtime() {
    return accountcreatedtime;
  }

  public void setAccountcreatedtime(java.sql.Timestamp accountcreatedtime) {
    this.accountcreatedtime = accountcreatedtime;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getToken(User user) {
    String token="";
    token= JWT.create().withAudience(user.getId())
            .sign(Algorithm.HMAC256(user.getPassword()));
    return token;
  }
}
