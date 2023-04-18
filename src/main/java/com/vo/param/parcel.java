package com.vo.param;

import lombok.Getter;
import lombok.Setter;

/**
 * FileName: EnterParam
 * Date: 2023/04/11
 * Description: 该类用作出 入库请求的数据对象 提交的数据
 */

@Getter
@Setter
public class parcel {

    private String id;

    private String place;

    private parcel(String id,String place){
        this.id = id;
        this.place = place;
    }
}
