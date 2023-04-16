package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.entity.User;
import com.mapper.UserMapper;
import com.service.UserService;
import com.vo.R;
import com.vo.param.InformationParam;
import com.vo.param.InitStockParam;
import com.vo.param.LoginParam;
import com.vo.param.RegisterParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


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

        return r.ok();
    }

    // 仓库初始化
    @Override
    public R initStock(InitStockParam initStockParam) {
        int length= initStockParam.getCapacity_x();
        int width= initStockParam.getCapacity_y();
        int[][][] warehouse = new int[length][width][3];
        int num = length/2 * width/2 /34;
        int count=0,code=1;
        int record=0;

        // 生成货架
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    warehouse[i][j][0] = code;//初始化将货架均匀的分为34个区域
                    count++;
                    if(count-record>num){
                        record=count;
                        code++;
                    }
                } else {
                    warehouse[i][j][0] = 0;
                }
            }
        }

        // 输出仓库
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(warehouse[i][j][0] + " ");
            }
            System.out.println();
        }

        return R.ok();
    }

    @Override
    public R information(InformationParam informationParam) {
        return null;
    }

}
