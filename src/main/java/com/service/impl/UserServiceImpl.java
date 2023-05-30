package com.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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


    @Override
    public R searchParce(String id,String userNickname) {
        R r = new R();
        User user = userMapper.selectById(id);
        if (user == null) {
            r.setMsg("用户不存在");
            return r;
        }
        if(!user.getName().equals(userNickname))
        {
            r.setMsg("用户名错误");
            return r;
        }
        r.data("userName", user.getName());
        r.data("userPhone", user.getContactnumber());
        r.data("stationName", user.getTransitstation());
        r.data("startTime", user.getAccountcreatedtime().toString());
        r.data("userEmail", user.getEmail());
        return r;
    }

    @Override
    public R modifyUserInformation(String id,ModifyUserInformationParam modifyUserInformationParam) {
        R r = new R();
        r.data("status_code", false);
        User user = userMapper.selectById(id);
        if (user == null) {
            return r;
        }
        if (modifyUserInformationParam.getUserName() != null) {
            user.setName(modifyUserInformationParam.getUserName());
        }
        if (modifyUserInformationParam.getUserPhone() != null) {
            user.setContactnumber(modifyUserInformationParam.getUserPhone());
        }
        if (!modifyUserInformationParam.getStationName().isEmpty()) {
            try {
                user.setAccountcreatedtime(Timestamp.valueOf(modifyUserInformationParam.getStartTime()));
            } catch (IllegalArgumentException e) {
                r.setMsg("时间格式错误");
                return r;
            }
        }
        if (modifyUserInformationParam.getStartTime() != null) {
            user.setAccountcreatedtime(Timestamp.valueOf(modifyUserInformationParam.getStartTime()));
        }
        if (modifyUserInformationParam.getUserEmail() != null) {
            user.setEmail(modifyUserInformationParam.getUserEmail());
        }
        userMapper.updateById(user);
        r.data("status_code", true);
       return r;
    }

    @Override
    public R modifyPassword(String id,ModifyPasswordParam modifyPasswordParam) {
        R r = new R();
        r.data("status_code", false);
        User user = userMapper.selectById(id);
        if (user == null) {
            r.setMsg("用户不存在");
            return r;
        }
        if (!(modifyPasswordParam.getPassword().equals(user.getPassword()))) {
            r.setMsg("密码错误");
            return r;
        }
        if (!(modifyPasswordParam.getUserNickname().equals(user.getName()))) {
            r.setMsg("用户名错误");
            return r;
        }
        user.setPassword(modifyPasswordParam.getPassword());
        userMapper.updateById(user);
        r.setMsg("密码修改成功");
        r.data("status_code", true);
        return r;

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