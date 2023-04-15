package com.vo.param;

import lombok.Getter;

/**
 * FileName: OutParam
 * Date: 2023/04/11
 * Description: 该类用于封装 根据id查询包裹 请求提交的数据
 */
@Getter
public class CheckParcelParam {
    // 数据传递顺序
    private String token; // token
    private String id; // 包裹ID

    public CheckParcelParam(String token,String id){
        this.token = token;
        this.id = id;
    }

}
