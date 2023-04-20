package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * FileName: Warehouse
 * Date: 2023/4/18
 * Description: Warehouse实体
 */
@Data
@Setter
@TableName("warehouse")
public class Warehouse {

    private int id;

    private int capacity_x;

    private int capacity_y;

    private int gate_machine;

    private int avg;

    private String username;

}
