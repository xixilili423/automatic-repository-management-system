package com.controller;

import com.service.UserService;
import com.vo.R;
import com.vo.param.InitStockParam;
import com.vo.param.LoginParam;
import com.vo.param.RegisterParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * FileName:  UserController
 * Date: 2023/04/01
 */

@RestController
@RequestMapping("/user")
//@CrossOrigin
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // 1.登陆--post保存
    @PostMapping("login")
    public R login(@RequestBody LoginParam loginParam) {
        return userService.login(loginParam);
    }

    // 2.注册--post保存
    @PostMapping("register")
    public R register(@RequestBody RegisterParam registerParam) {
        return userService.register(registerParam);
    }

    // 3.仓库初始化
    @PostMapping("/initStock")
    public R initStock(@RequestBody InitStockParam initStockParam) {
        System.out.println(initStockParam.toString());
        return userService.initStock(initStockParam);
    }
}
