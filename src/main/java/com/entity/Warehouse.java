package com.entity;


public class Warehouse {

  private long id;
  private long X;
  private long Y;
  private long gate;
  private long avg;
  private String username;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getX() {
    return X;
  }

  public void setX(long X) {
    this.X = X;
  }


  public long getY() {
    return Y;
  }

  public void setY(long Y) {
    this.Y = Y;
  }


  public long getGate() {
    return gate;
  }

  public void setGate(long gate) {
    this.gate = gate;
  }


  public long getAvg() {
    return avg;
  }

  public void setAvg(long avg) {
    this.avg = avg;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
