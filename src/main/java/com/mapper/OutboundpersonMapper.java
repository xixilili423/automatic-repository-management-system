package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.entity.Inbound;
import com.entity.Outboundperson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OutboundpersonMapper extends BaseMapper<Outboundperson> {
    @Select("SELECT * FROM outboundperson WHERE outboundpersonid = #{id}")
    Outboundperson selectById(Long id);
}
