package com.vo.param;

import lombok.Getter;

@Getter
public class ChangeParam {
    private String pre_password;
    private String new_password;
    private String token;

    public ChangeParam(String pre_password, String new_password, String token) {
        this.pre_password = pre_password;
        this.new_password = new_password;
        this.token = token;
    }


}
