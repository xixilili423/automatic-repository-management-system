package com.vo.param;

import lombok.Getter;

/**
 * FileName: ChangePasswordParam
 * Date: 2023/04/23
 * Description: 该类用于封装 修改密码 请求提交的数据
 */
@Getter
public class ChangePasswordParam {
    String pre_password; // 旧密码

    String new_password; // 新密码

    String token; // 用户鉴权

}
