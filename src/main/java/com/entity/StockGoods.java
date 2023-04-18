package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * FileName: StockGoods
 * Date: 2023/4/18
 * Description: stock_goods实体
 */
@Data
@TableName("stock_goods")
public class StockGoods {

    private int id;

    private String package_id;

    private int warehouse_id;

    private int location_x;

    private int location_y;

    private String status;

}
