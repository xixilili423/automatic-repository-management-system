package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.db.entity.User;
import org.springframework.stereotype.Repository;


@Repository
//@Mapper
public interface UserMapper extends BaseMapper<User> {

}
