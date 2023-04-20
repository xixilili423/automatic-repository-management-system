package com.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.StockIn;
import com.entity.StockOut;
import com.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnterMapper extends BaseMapper<StockIn> {

}
