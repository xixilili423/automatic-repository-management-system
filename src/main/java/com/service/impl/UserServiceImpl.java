package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.controller.ApplicationContextHelperUtil;
import com.mapper.UserMapper;
import com.service.UserService;
import com.vo.R;
import com.vo.param.InformationParam;
import com.vo.param.InitStockParm;
import com.vo.param.LoginParam;
import com.vo.param.RegisterParam;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * FileName:  UserServiceImpl
 * Date: 2023/04/13
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    // 登陆

    UserMapper userMapper= ApplicationContextHelperUtil.getBean(UserMapper.class);
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

    // 仓库初始化
    @Override
    public R initStock(InitStockParm initStockParm) {
        return R.ok();
    }

    // 主页信息获取
    @Override
    public R information(InformationParam informationParam){ return R.ok(); }

}
