package com.vo.param;

import lombok.Getter;
import lombok.Setter;

/**
 * FileName: PacelList
 * Date: 2023/04/22
 * Description: 该类用作出 出入库请求返回响应 的类
 */

@Getter
@Setter
public class ParcelList {
    String parcel_id;
    boolean status;
    String location_xy;

    public ParcelList(String parcel_id, boolean status, String location_xy){
        this.parcel_id = parcel_id;
        this.status = status;
        this.location_xy = location_xy;
    }
}
