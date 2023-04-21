package com.vo.param;


import lombok.Getter;

/**
 * FileName: InformationParm
 * Date: 2023/04/03
 * Description: 该类用于封装 主页信息获取 请求提交的数据
 */

@Getter
public class InitData {

    // 仓库x
    int capacity_x;

    // 仓库y
    int capacity_y;

    // 闸机数
    int gate_machine;

    // 小车数
    int avg;

    public InitData(int capacity_x,int capacity_y,int gate_machine,int avg){
        this.capacity_x = capacity_x;
        this.capacity_y = capacity_y;
        this.gate_machine = gate_machine;
        this.avg = avg;
    }

}
