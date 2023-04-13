package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.mapper.*;
import com.service.UserService;
import com.vo.R;
import com.vo.param.InitStockParm;
import com.vo.param.LoginParam;
import com.vo.param.RegisterParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * FileName:  UserServiceImpl
 * Date: 2023/04/13
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
//public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    // 登陆
    @Override
    public R login(LoginParam loginParam){
        return R.ok();
    }

    // 注册
    @Override
    public R register(RegisterParam registerParam) {
        return R.ok();
    }

    // 仓库初始化
    @Override
    public R initStock(InitStockParm initStockParm) {
        return R.ok();
    }

}
