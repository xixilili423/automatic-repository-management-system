package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Inbound;
import com.entity.Package;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InboundMapper extends BaseMapper<Inbound> {
    @Select("SELECT * FROM inbound WHERE inboundid = #{id}")
    Inbound selectById(Long id);
}
