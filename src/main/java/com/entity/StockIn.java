package com.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * FileName: Stockin
 * Date: 2023/4/18
 * Description: stock_in实体
 */
@Data
@TableName("stock_in")
public class StockIn {

    private int id; // 记录的id

    private String parcel;

    private int warehouse;

    private int x;

    private int y;

    private String address; // 目的地所属地区

    private String time; // 数据库里面是datatime类型，精确到秒，需要进行转换

    // private String status;
}
