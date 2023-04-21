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
    // 需要测试下parcelInList怎么接收
//    private Array[] parcelInList; // 包裹id，目的地

    private Parcel parcelInList[]; // 包裹id，目的地
    private String token; // token

    public EnterParam(Parcel parcelInList[], String token){
        this.parcelInList = parcelInList;
        this.token = token;
    }

}
