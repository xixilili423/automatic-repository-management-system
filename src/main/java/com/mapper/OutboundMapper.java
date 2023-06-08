package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Outbound;
import com.entity.Outboundperson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OutboundMapper extends BaseMapper<Outbound> {
    @Select("SELECT * FROM outbound WHERE outboundid = #{id}")
    Outbound selectById(Long id);
}
