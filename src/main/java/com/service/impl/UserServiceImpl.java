package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.mapper.UserMapper;
import com.mapper.WareMapper;
import com.service.UserService;
import com.vo.R;
import com.vo.param.InformationParam;
import com.vo.param.InitStockParam;
import com.vo.param.LoginParam;
import com.vo.param.RegisterParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;


/**
 * FileName:  UserServiceImpl
 * Date: 2023/04/13
 */
@Service
//@Transactional
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    private final UserMapper userMapper;

    // 登陆

    @Override
    public R login(LoginParam loginParam){

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("username",loginParam.getUsername()).eq("password",loginParam.getPassword());
            User user = userMapper.selectOne(queryWrapper);
            R r= new R();
            if (user != null && user.getPassword().equals(loginParam.getPassword())) {
               String token= user.getToken(user);
               r.data("status_code","true");
               r.data("user_id","0");
               r.data("token",token);
                return r;
            } else {
                r.data("status_code","false");
                r.data("user_id","0");
                r.data("token","");
                return r;
            }
    }

    // 注册
    @Override
    public R register(RegisterParam registerParam) {

        R r= new R();

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",registerParam.getUsername()).eq("password",registerParam.getPassword());
        User u = userMapper.selectOne(queryWrapper);
        if(u==null)
        {
            User user=new User();
            user.setUsername(registerParam.getUsername());
            user.setPassword(registerParam.getPassword());
            userMapper.insert(user);
            r.data("status_code","true");
        }
        else {
            r.data("status_code","false");
        }

        return R.ok();
    }

    // 主页请求用户信息
    @Override
    public R getInformation(String token) {

        return R.ok();
    }
}
