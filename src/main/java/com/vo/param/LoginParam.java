package com.vo.param;
import lombok.Getter;
/**
 * FileName: LoginParam
 * Date: 2023/04/02
 * Description: 该类用于封装表单提交的数据
 */
@Getter
public class LoginParam {
    // 数据传递顺序
    private String username; //用户名
    private String email; //邮箱
    private String password; //密码
    private String passwordConfirm; //确认密码
    private String code; //验证码

    // 登陆
    public LoginParam(String username,String password){
        this.username = username;
        this.password = password;
    }

    public LoginParam(String username,String email,String password,String passwordConfirm,String code){
        this.username=username;
        this.email=email;
        this.password=password;
        this.passwordConfirm=passwordConfirm;
        this.code=code;
    }

    public LoginParam(String email,String password,String code){
        this.email=email;
        this.password=password;
        this.code=code;
    }
}
