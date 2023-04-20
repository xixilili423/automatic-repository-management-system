package com.service.impl;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.StockIn;
import com.entity.StockOut;
import com.entity.User;
import com.mapper.EnterMapper;
import com.mapper.OutMapper;
import com.mapper.UserMapper;
import com.service.EnterService;
import com.sun.tools.javac.comp.Enter;
import com.vo.R;
import com.vo.param.EnterParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterServiceImpl extends ServiceImpl<EnterMapper, StockIn> implements EnterService {
    UserMapper userMapper;
   EnterMapper enterMapper;
    List<StockIn> stock_in;
    // 入库请求
    @Override
    public R enterStock(EnterParam enterParam){

        return R.ok();
    }

    // 获取入库记录表格
    @Override
    public R getInTable(String token){
        R r= new R();
        QueryWrapper<User> queryWrapper1=new QueryWrapper<>();
        String username = JWT.decode(token).getAudience().get(0);
        queryWrapper1.eq("username",username);
        boolean user = userMapper.exists(queryWrapper1);
        if(user) {
            QueryWrapper<StockIn> queryWrapper = new QueryWrapper<>();
            List<StockIn> stock_in=enterMapper.selectList(queryWrapper);
            r.data("stock_in",stock_in);
            return r.ok();
        }
        else
        {
            return r.ok();
        }

    }
    }



