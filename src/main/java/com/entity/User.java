package com.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public String getToken(User user) {
        return JWT.create().withAudience(user.getUsername())
                .sign(Algorithm.HMAC256(user.getPassword()));
    }
    @TableId
    private String username; // 用户名

    private String password; // 密码

    private String address; // 所属中转站

    private String phone; // 电话

    private String totalcost; // 费用

}
