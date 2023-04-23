package com.controller;

import com.service.UserService;
import com.vo.R;
import com.vo.param.*;
import lombok.AllArgsConstructor;
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
    public R login(@RequestBody LoginParam loginParam) {
        return userService.login(loginParam);
    }

    // 注册--post保存
    @PostMapping("register")
    public R register(@RequestBody RegisterParam registerParam) {
        return userService.register(registerParam);
    }

    // 获取主页信息--get
    @GetMapping("information")
    public R getInformation(@RequestParam(value="token") String token) { return userService.getInformation(token); }

    // 修改密码
    @PostMapping("changePassword")
    public R changePassword(@RequestBody ChangePasswordParam changePasswordParam) { return  userService.changePassword(changePasswordParam); }

    // 修改个人信息
    @PostMapping("changeInfo")
    public R changeInfo(@RequestBody ChangeInfoParam changeInfoParam) { return userService.changeInfo(changeInfoParam); }
}
