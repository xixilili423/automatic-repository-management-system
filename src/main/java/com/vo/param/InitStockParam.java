package com.vo.param;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FileName: InitStockParm
 * Date: 2023/04/03
 * Description: 该类用于封装 初始化仓库 表单提交的数据
 */
@Data
@AllArgsConstructor
public class InitStockParam {
    // 仓库初始化
    private int capacity_x; // 仓库x
    private int capacity_y; // 仓库y
    private int avg; // 仓库小车数
    private int gateMachine; // 仓库门数量
    private String token; //鉴权
}
