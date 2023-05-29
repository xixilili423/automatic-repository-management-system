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
    /**
     * 所属上司用户名（ID）
     */
    private String managerID;
    /**
     * 密码，最长32个字符
     */
    private String password;
    /**
     * 用户名，最长32个字符
     */
    private String userID;
    /**
     * 真实姓名
     */
    private String userName;
}
