package com.vo.param;
import lombok.Getter;
/**
 * FileName: LoginParam
 * Date: 2023/04/02
 * Description: 该类用于封装 登陆 请求提交的数据
 */
@Getter
public class LoginParam {
    // 数据传递顺序
    private String username; //用户名
    private String password; //密码

    // private Boolean initStockOrNot; //初始化仓库与否，0表示未初始化，1表示已经初始化

    // 仓库目前长相

    // 登陆
    public LoginParam(String username,String password){
        this.username = username;
        this.password = password;
        // 判断初始化仓库与否，从数据库获取
    }

}
