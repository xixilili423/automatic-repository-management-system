package com.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.Package;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ParcelMapper extends BaseMapper<Package> {
    @Select("SELECT packageid, shippername, shippercontact, shipperaddress, consigneename, consigneecontact, consigneeaddress, status FROM package WHERE packageid = #{id}")
    Package selectById(Long id);
}
