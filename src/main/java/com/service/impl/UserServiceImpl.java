package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.entity.Warehouse;
import com.mapper.UserMapper;
import com.mapper.WareMapper;
import com.service.UserService;
import com.vo.R;
import com.vo.param.InformationParam;
import com.vo.param.InitStockParam;
import com.vo.param.LoginParam;
import com.vo.param.RegisterParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import java.util.List;


/**
 * FileName:  UserServiceImpl
 * Date: 2023/04/13
 */
@Service
//@Transactional
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    private final UserMapper userMapper;
    @Autowired
    private final WareMapper wareMapper;

    // 登陆
    @Override
    public R login(LoginParam loginParam){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("username",loginParam.getUsername()).eq("password",loginParam.getPassword());
            User user = userMapper.selectOne(queryWrapper);
            R r= new R();
            if (user != null && user.getPassword().equals(loginParam.getPassword())) {
                QueryWrapper<Warehouse> queryWrapper1=new QueryWrapper<>();
                queryWrapper.eq("username",user.getUsername());
                List<Warehouse> Ware = wareMapper.selectList(queryWrapper1);//查询用户是否创建过仓库
               String token= user.getToken(user);
               r.data("user_id","0");
               r.data("token",token);
                r.data("status_code",true);
                if(!Ware.isEmpty()) {
                    r.data("warehouse",true);
                } else {
                    r.data("warehouse",false);
                }
                return r;
            } else {
                r.data("warehouse",false);
                r.data("status_code",false);
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
        queryWrapper.eq("username",registerParam.getUsername());
        boolean u = userMapper.exists(queryWrapper);
        if(!u)
        {
            User user=new User();
            user.setUsername(registerParam.getUsername());
            user.setPassword(registerParam.getPassword());
            userMapper.insert(user);
            r.data("status_code","true");
            return r;
        }
        else {
            r.data("status_code","false");
            return r;
        }
    }

    // 主页请求用户信息
    @Override
    public R getInformation(String token) {
        R r = new R();
        if(token.equals("")){
            r.data("status_code",false);
            return r;
        }
        // 鉴权，获取username
        String username = JWT.decode(token).getAudience().get(0);
        System.out.println(username);
        // 判断该username是否存在
        if(username != null){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",username);
            User user = userMapper.selectOne(queryWrapper);
            String phone = user.getPhone();
            String address = user.getAddress();
            String total_cost = user.getTotalcost();
            r.data("status_code",true);
            r.data("user_name",username);
            r.data("phone",phone);
            r.data("address",address);
            r.data("total_cost",total_cost);
        }
        return r;
    }
}
