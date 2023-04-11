package com.vo.param;
import lombok.Getter;

/**
 * FileName: InitStockParm
 * Date: 2023/04/03
 * Description: 该类用于封装 初始化仓库 表单提交的数据
 */
@Getter
public class InitStockParm {
    // 仓库初始化
    private int capacity_x; // 仓库x
    private int capacity_y; // 仓库y
    private int avg; // 仓库小车数
    private int gateMachine; // 仓库门数量
    private String username; //用户名

    public InitStockParm(int capacity_x, int capacity_y, int avg, int gateMachine, String username){
        this.capacity_x = capacity_x;
        this.capacity_y = capacity_y;
        this.avg = avg;
        this.gateMachine = gateMachine;
        this.username = username;
    }
}
