package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * FileName: Stockout
 * Date: 2023/4/18
 * Description: stock_out实体
 */

@Data
@TableName("stock_out")

public class StockOut {

    private int id;

    private String parcel;

    private int warehouse;

    private int x;

    private int y;

    private String address; // 目的地所属地区

    private String time;// 数据库里面是datatime类型，精确到秒，需要进行转换
}
