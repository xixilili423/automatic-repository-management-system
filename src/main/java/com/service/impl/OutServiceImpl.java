package com.service.impl;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.StockOut;
import com.entity.User;
import com.mapper.OutMapper;
import com.mapper.UserMapper;
import com.service.OutService;
import com.vo.R;
import com.vo.param.OutParam;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OutServiceImpl extends ServiceImpl<OutMapper, StockOut> implements OutService {

    UserMapper userMapper;
    OutMapper outMapper;
    List<StockOut> stock_out;
    // 出库请求
    @Override
    public R outStock(OutParam outParam){
        return R.ok();
    }

    // 获取出库记录表格
    @Override
    public R getOutTable(String token){
        R r= new R();
        QueryWrapper<User> queryWrapper1=new QueryWrapper<>();
        String username = JWT.decode(token).getAudience().get(0);
        queryWrapper1.eq("username",username);
        boolean user = userMapper.exists(queryWrapper1);
        if(user) {
            QueryWrapper<StockOut> queryWrapper = new QueryWrapper<>();
            List<StockOut> stock_out=outMapper.selectList(queryWrapper);
            r.data("stock_out",stock_out);
            return R.ok();
        }
        else
        {
            return R.ok();
        }

    }
}
