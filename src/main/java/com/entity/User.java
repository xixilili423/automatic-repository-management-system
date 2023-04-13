package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * FileName: User
 * Date: 2023/4/13
 * Description: User实体
 */
@Data
@TableName("user")
public class User {

    private String username; // 用户名

    private String password; // 密码

}
