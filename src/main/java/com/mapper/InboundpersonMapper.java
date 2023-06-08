package com.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Outboundperson;
import com.entity.Warehouseperson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InboundpersonMapper  extends BaseMapper<Warehouseperson> {
    @Select("SELECT * FROM  warehouseperson WHERE warehousepersonid = #{id}")
    Warehouseperson selectById(Long id);
}
