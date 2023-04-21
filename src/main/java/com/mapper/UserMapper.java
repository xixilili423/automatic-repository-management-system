package com.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.User;
import com.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
