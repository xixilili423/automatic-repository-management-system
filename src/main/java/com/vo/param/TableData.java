package com.vo.param;


import lombok.Getter;
import lombok.Setter;

/**
 * FileName: TableData
 * Date: 2023/04/11
 * Description: 该类用作出 获取出入库记录参数 的类
 */
@Getter
@Setter

public class TableData {

    private String id; // 包裹id

    private String time; //出入库时间

    private String location_xy; // 存放货架

    private String address; // 目的地所属地区

    public TableData(String id, String time, String location_xy, String address){
        this.id = id;
        this.time = time;
        this.location_xy = location_xy;
        this.address=address;
    }

}
