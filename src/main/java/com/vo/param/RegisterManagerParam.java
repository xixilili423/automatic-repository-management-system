package com.vo.param;

@lombok.Data
public class RegisterManagerParam {
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名（数字和字母）
     */
    private String userID;
    /**
     * 真实姓名
     */
    private String userName;
}