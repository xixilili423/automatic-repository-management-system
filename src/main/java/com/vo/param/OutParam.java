package com.vo.param;

import lombok.Getter;

/**
 * FileName: OutParam
 * Date: 2023/04/11
 * Description: 该类用于封装 出库请求 提交的数据
 */
@Getter
public class OutParam {
    // 数据传递顺序
    private Parcel parcelOutList[]; // 包裹id，目的地
    private String userName; // 用户名（还是说用token？)

    public OutParam(Parcel parcelOutList[], String userName){
        this.parcelOutList = parcelOutList;
        this.userName = userName;
    }
}
