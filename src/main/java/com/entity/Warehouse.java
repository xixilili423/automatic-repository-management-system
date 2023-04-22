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

    private int x;

    private int y;

    private int gate;

    private int avg;

    private String username;

}
