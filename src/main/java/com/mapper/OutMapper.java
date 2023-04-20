package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.StockOut;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OutMapper extends BaseMapper<StockOut> {
}
