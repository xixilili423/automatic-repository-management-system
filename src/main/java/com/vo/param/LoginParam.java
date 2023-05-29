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
    /**
     * 登录密码
     */
    private String password;
    /**
     * 登录用户id
     */
    private String userID;
    // private Boolean initStockOrNot; //初始化仓库与否，0表示未初始化，1表示已经初始化

    // 仓库目前长相

    // 登陆
    public LoginParam(String userID,String password){
        this.userID = userID;
        this.password = password;
        // 判断初始化仓库与否，从数据库获取
    }

}
