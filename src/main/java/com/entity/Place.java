package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("place")
public class Place {

  private long id;
  
  private String placeName;

}
