package com.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.service.UserService;


@Controller
public class MyController {
@Autowired
UserService userService=(UserService) ApplicationContextHelperUtil.getBean( UserService.class);;
    @PostMapping("/api/login")
    @CrossOrigin
    @ResponseBody
    public void login(int length ,int width)
    {
//        user user1=new user();
//        user1.setEmail(email);
//        user1.setPassword(password);
//        boolean re=userService.Login(user1);
//        System.out.println("action");
//        if(re)
//        {
//          return Result.success();
//        }
//      else{
//          return  Result.error("40","failed");
//      }
        int[][][] warehouse = new int[length][width][3];

        // 生成货架
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    warehouse[i][j][0] = 1;
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
    }
}
