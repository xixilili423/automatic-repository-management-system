package com.vo.param;

import lombok.Getter;

import java.lang.reflect.Array;

/**
 * FileName: OutParam
 * Date: 2023/04/11
 * Description: 该类用于封装 出库请求 提交的数据
 */
@Getter
public class OutParam {
    // 数据传递顺序
    private parcel parcelOutList[]; // 包裹id，目的地
    private String userName; // 用户名（还是说用token？)

    public OutParam(parcel parcelInList[], String userName){
        this.parcelOutList = parcelOutList;
        this.userName = userName;
    }
}
