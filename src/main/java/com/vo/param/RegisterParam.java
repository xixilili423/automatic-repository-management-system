package com.vo.param;
import lombok.Getter;

/**
 * FileName: registerParam
 * Date: 2023/04/03
 * Description: 该类用于封装注册表单提交的数据
 */
@Getter
public class RegisterParam {
    // 注册
    private String username; // 用户名
    private String password; // 密码

    public RegisterParam(String username, String password, String passwordConfirm){
        this.username = username;
        this.password = password;
    }

}
