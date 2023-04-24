package com.vo.param;

import lombok.Getter;

@Getter
public class ChangeInfoParam {
      private  String  phone;
      private  String address;
      private String token;
      public  ChangeInfoParam(String phone,String address,String token)
      {
            this.phone=phone;
            this.address=address;
            this.token=token;
      }
}
