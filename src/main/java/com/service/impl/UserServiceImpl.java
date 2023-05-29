package com.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;

import com.entity.*;
import com.mapper.*;
import com.service.UserService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.sql.Timestamp;
import java.util.List;


/**
 * FileName:  UserServiceImpl
 * Date: 2023/04/13
 */
@Service
//@Transactional
@AllArgsConstructor
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final OutboundpersonMapper outboundpersonMapper;
    @Autowired
    private final InboundpersonMapper inboundpersonMapper;
    @Autowired
    private final CustomerMapper customerMapper;
    @Autowired
    private final WareMapper wareMapper;
    @Autowired
    private final ShelfMapper shelfMapper;

    // 登陆
    @Override
    public R login(LoginParam loginParam) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", loginParam.getUserID()).eq("password", loginParam.getPassword());
        User user = userMapper.selectOne(queryWrapper);
        R r = new R();
        if (user != null && user.getPassword().equals(loginParam.getPassword())) {
            System.out.println(user.getId());
            String token = user.getToken(user);
            r.data("user_id", user.getId());
            r.data("token", token);
            r.data("userName", user.getName());
            r.data("authority", user.getPermission());
            r.data("status_code", true);
            return r;
        } else {
            r.data("status_code", false);
            r.data("user_id", 0);
            r.data("token", "");
            return r;
        }
    }

    // 注册
    @Override
    public R register(RegisterParam registerParam) {
        R r = new R();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper.eq("id", registerParam.getUserID());
        queryWrapper1.eq("id", registerParam.getManagerID()).eq("permission", registerParam.getManagerID());
        boolean u = userMapper.exists(queryWrapper);
        boolean u1 = userMapper.exists(queryWrapper1);
        System.out.println(u);
        if (!u && !u1) {

            User user = new User();
            user.setEmail("");
            user.setId(registerParam.getUserID());
            user.setName(registerParam.getUserName());
            user.setAccountcreatedtime(Timestamp.valueOf(LocalDateTime.now()));
            user.setPermission("user");
            user.setPassword(registerParam.getPassword());
            userMapper.insert(user);
            r.data("status_code", true);
            return r;
        } else {
            r.data("status_code", false);
            r.data("errorMag", "user has existed");
            return r;
        }
    }

    //修改密码
    @Override
    public R changePassword(ChangeParam changeParam) {
        R r = new R();
        // 鉴权，获取username
        String username = JWT.decode(changeParam.getToken()).getAudience().get(0);
        System.out.println(username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        // 判断该username是否存在
        if (user != null && user.getPassword().equals(changeParam.getPre_password())) {
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username", username).set("password", changeParam.getNew_password());
            userMapper.update(user, updateWrapper);
            r.data("status_code", true);
            return r;
        } else {
            r.data("status_code", false);
            return r;
        }
    }

    @Override
    public R registerManager(RegisterManagerParam registerManagerParam) {
        R r = new R();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", registerManagerParam.getUserID());
        boolean u = userMapper.exists(queryWrapper);
        System.out.println(u);
        if (!u) {
            User user = new User();
            user.setEmail("");
            user.setId(registerManagerParam.getUserID());
            user.setName(registerManagerParam.getUserName());
            user.setAccountcreatedtime(Timestamp.valueOf(LocalDateTime.now()));
            user.setPermission("manager");
            user.setPassword(registerManagerParam.getPassword());
            userMapper.insert(user);
            r.data("status_code", true);
            return r;
        } else {
            r.data("status_code", false);
            return r;
        }

    }

    // 主页请求用户信息
    @Override
    public R getInformation(String token) {
        R r = new R();
        if (token.equals("")) {
            r.data("status_code", false);
            return r;
        }
        // 鉴权，获取username
        String username = JWT.decode(token).getAudience().get(0);
        System.out.println(username);
        // 判断该username是否存在
        if (username != null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            User user = userMapper.selectOne(queryWrapper);
            // String phone = user.getPhone();
            //String address = user.getAddress();
            //String total_cost = user.getTotalcost();
            r.data("status_code", true);
            r.data("user_name", username);
            // r.data("phone",phone);
            //r.data("address",address);
            //r.data("total_cost",total_cost);
        }
        return r;
    }

    //修改个人信息
    @Override
    public R changeInformation(ChangeInfoParam changeInfoParam) {
        R r = new R();
        // 鉴权，获取username
        String username = JWT.decode(changeInfoParam.getToken()).getAudience().get(0);
        System.out.println(username);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        // 判断该username是否存在
        if (user != null) {
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username", username);
            if (changeInfoParam.getPhone() != null) {
                updateWrapper.set("phone", changeInfoParam.getPhone());
                System.out.println(changeInfoParam.getPhone());
            }
            if (changeInfoParam.getAddress() != null) {
                System.out.println(changeInfoParam.getAddress());
                updateWrapper.set("address", changeInfoParam.getAddress());
            }
            userMapper.update(user, updateWrapper);
            r.data("status_code", true);
            return r;
        } else {
            r.data("status_code", false);
            return r;
        }
    }

    @Override
    public User findUserById(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public R managerHomePage(String id) {
        R r = new R();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission", "user");
        long staffNum = userMapper.selectCount(queryWrapper);
        QueryWrapper<Shelf> queryWrapper1 = new QueryWrapper<>();
        long shelfNum = shelfMapper.selectCount(queryWrapper1);
        QueryWrapper<Customer> queryWrapper2 = new QueryWrapper<>();
        long companyNum = customerMapper.selectCount(queryWrapper2);
        QueryWrapper<Warehouseperson> queryWrapper3 = new QueryWrapper<>();
        long inPeopleNum = inboundpersonMapper.selectCount(queryWrapper3);
        QueryWrapper<Outboundperson> queryWrapper4 = new QueryWrapper<>();
        long outPeopleNum = outboundpersonMapper.selectCount(queryWrapper4);
        r.data("status_code", true);
        r.data("staffNum", staffNum);
        r.data("shelfNum", shelfNum);
        r.data("companyNum", companyNum);
        r.data("inPeopleNum", inPeopleNum);
        r.data("outPeopleNum", outPeopleNum);

        return r;
    }

    @Override
    public R userHomePage(String id) {
        R r = new R();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("permission", "manager");
        User u = userMapper.selectOne(queryWrapper);
        if(u!=null)
        {
            String managerName = u.getName();
            QueryWrapper<Shelf> queryWrapper1 = new QueryWrapper<>();
            long shelfNum = shelfMapper.selectCount(queryWrapper1);
            QueryWrapper<Customer> queryWrapper2 = new QueryWrapper<>();
            long companyNum = customerMapper.selectCount(queryWrapper2);
            QueryWrapper<Warehouseperson> queryWrapper3 = new QueryWrapper<>();
            long inPeopleNum = inboundpersonMapper.selectCount(queryWrapper3);
            QueryWrapper<Outboundperson> queryWrapper4 = new QueryWrapper<>();
            long outPeopleNum = outboundpersonMapper.selectCount(queryWrapper4);
            r.data("status_code", true);
            r.data("managerName", managerName);
            r.data("shelfNum", shelfNum);
            r.data("companyNum", companyNum);
            r.data("inPeopleNum", inPeopleNum);
            r.data("outPeopleNum", outPeopleNum);

            return r;
        }
        else
        {
            r.data("status_code", false);
            return r;
        }
    }
}