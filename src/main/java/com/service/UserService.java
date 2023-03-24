package com.service;
import com.controller.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mapper.Usermapper;
import com.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mapper.*;


@Service
public class UserService {

    @Autowired
    private Usermapper usermapper=(Usermapper)  ApplicationContextHelperUtil.getBean(Usermapper.class);
    public boolean Login(user user1){
        QueryWrapper<user> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("email",user1.getEmail()).eq("password",user1.getPassword());
        return usermapper.equals(userQueryWrapper);
    }
}
