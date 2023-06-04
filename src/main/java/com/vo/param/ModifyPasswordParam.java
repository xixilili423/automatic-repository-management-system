package com.vo.param;

@lombok.Data
public class ModifyPasswordParam {
    /**
     * 旧密码
     */
    private String password;
    /**
     * 新密码
     */
    private String passwordNew;
    /**
     * 用户名
     */
    private String userNickname;
}