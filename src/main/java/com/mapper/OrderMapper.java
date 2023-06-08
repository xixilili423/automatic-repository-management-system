package com.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Ordertable;
import com.entity.Warehouseperson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper  extends BaseMapper<Ordertable> {
    @Select("SELECT * FROM  order WHERE orderid = #{id}")
    Ordertable selectById(Long id);
}
