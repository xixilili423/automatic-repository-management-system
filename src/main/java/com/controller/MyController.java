package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.config.Result;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;
import com.mapper.Usermapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.pojo.user;
import com.service.UserService;


@Controller
public class MyController {
@Autowired
UserService userService=(UserService) ApplicationContextHelperUtil.getBean( UserService.class);;
    @PostMapping("/api/login")
    @CrossOrigin
    @ResponseBody
    public Result<?> login(  String email,String password )
    {
        user user1=new user();
        user1.setEmail(email);
        user1.setPassword(password);
        boolean re=userService.Login(user1);
        System.out.println("action");
        if(re)
        {
          return Result.success();
        }
      else{
          return  Result.error("404","failed");
      }
    }
}
