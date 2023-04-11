package com.vo.param;

import lombok.Getter;

/**
 * FileName: EnterParam
 * Date: 2023/04/11
 * Description: 该类用于封装 入库请求 提交的数据
 */
@Getter
public class EnterParam {
    // 数据传递顺序
    private String id; // 包裹ID
    private String address; // 包裹目的地
    private String userName; // 用户名（还是说用token？）

    public EnterParam(String id,String address){
        this.id = id;
        this.address = address;
    }

}
