package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Inbound;
import com.entity.Shelf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShelfMapper extends BaseMapper<Shelf> {
    @Select("SELECT * FROM shelf WHERE shelfid = #{id}")
    Shelf selectById(Long id);
}
