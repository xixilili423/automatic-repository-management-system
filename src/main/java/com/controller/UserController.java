package com.controller;

import com.annotation.PassToken;
import com.annotation.UserLoginToken;
import com.service.UserService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

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

    // 登陆--post保存
    @PostMapping("login")
    @PassToken
    public R login(@RequestBody LoginParam loginParam) {
        return userService.login(loginParam);
    }

    // 注册--post保存
    @PostMapping("register")
    @PassToken
    public R register(@RequestBody RegisterParam registerParam) {
        return userService.register(registerParam);
    }

    // 获取主页信息--get

    @PostMapping("registerManager")
    @PassToken
    public R registerManager(@RequestBody RegisterManagerParam registerManagerParam)
    {
        return  userService.registerManager(registerManagerParam);
    }
    @GetMapping("managerHomePage")
    @UserLoginToken
    public R managerHomePage(@RequestAttribute(value="id") String id)
    {
        return userService.managerHomePage(id);
    }
    @GetMapping("userHomePage")
    @UserLoginToken
    public R userHomePage(@RequestAttribute(value="id") String id)
    {
        return  userService.userHomePage(id);
    }
}
