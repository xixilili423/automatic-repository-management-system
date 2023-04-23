package com.vo.param;

import lombok.Getter;

/**
 * FileName: ChangeInfoParam
 * Date: 2023/04/23
 * Description: 该类用于封装 修改个人信息 请求提交的数据
 */
@Getter
public class ChangeInfoParam {

    String phone; // 手机号

    String address; // 所属中转站

    String token; // token

}
