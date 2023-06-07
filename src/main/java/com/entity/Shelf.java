package com.entity;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public class Shelf {

  private String shelfid;
  private long areaid;
  private long capacity;
  private long remainingcapacity;


  public String getShelfid() {
    return shelfid;
  }

  public void setShelfid(String shelfid) {
    this.shelfid = shelfid;
  }


  public long getAreaid() {
    return areaid;
  }

  public void setAreaid(long areaid) {
    this.areaid = areaid;
  }


  public long getCapacity() {
    return capacity;
  }

  public void setCapacity(long capacity) {
    this.capacity = capacity;
  }


  public long getRemainingcapacity() {
    return remainingcapacity;
  }

  public void setRemainingcapacity(long remainingcapacity) {
    this.remainingcapacity = remainingcapacity;
  }
  }

