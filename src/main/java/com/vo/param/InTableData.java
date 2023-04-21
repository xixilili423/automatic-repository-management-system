package com.vo.param;


import lombok.Getter;
import lombok.Setter;

/**
 * FileName: EnterParam
 * Date: 2023/04/11
 * Description: 该类用作出 获取入库记录参数 的类
 */
@Getter
@Setter

public class InTableData {

    private String id; // 包裹id

    private String in_time; //入库时间

    private String location_xy; // 存放货架

    private String address; // 目的地所属地区

    public InTableData(String id, String in_time, String location_xy, String address){
        this.id = id;
        this.in_time = in_time;
        this.location_xy = location_xy;
        this.address=address;
    }

}
