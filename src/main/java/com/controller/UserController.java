package com.controller;

import com.service.UserService;
import com.vo.R;
import com.vo.param.InformationParam;
import com.vo.param.InitStockParm;
import com.vo.param.LoginParam;
import com.vo.param.RegisterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName:  UserController
 * Date: 2023/04/01
 */

@RestController
@RequestMapping("/user")
@CrossOrigin

public class UserController {
    @Autowired
    private UserService userService;

    // 1.登陆--post保存
    @PostMapping("login")
    public R login(@RequestBody LoginParam loginParam) {
        return userService.login(loginParam);
    }

    // 2.注册--post保存
    @PostMapping("register")
    public R register(@RequestBody RegisterParam registerParam){ return userService.register(registerParam); }

    // 3.仓库初始化
    @PostMapping("initStock")
    public R initStock(@RequestBody InitStockParm initStockParm){ return userService.initStock(initStockParm); }

    // 4. 主页信息获取
    @PostMapping("information")
    public R information(@RequestBody InformationParam informationParam){ return userService.information(informationParam); }

}
