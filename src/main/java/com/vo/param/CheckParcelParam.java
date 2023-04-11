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
    private String id; // 包裹ID
    private String[][][] inTableData; // 入库表记录数据(未确定）
    private String[][][] outTableData; // 出库表（未确定）

    public CheckParcelParam(String id){
        this.id = id;
    }

}
