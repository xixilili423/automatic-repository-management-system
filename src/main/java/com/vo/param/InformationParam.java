package com.vo.param;

import lombok.Getter;

/**
 * FileName: InformationParm
 * Date: 2023/04/03
 * Description: 该类用于封装 主页信息获取 请求提交的数据
 */
@Getter
public class InformationParam {
    private String token;

    public InformationParam(String token){
        this.token=token;
    }

}
