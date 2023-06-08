package com.controller;

import com.annotation.UserLoginToken;
import com.service.UserService;
import com.vo.R;
import com.vo.param.ChangeInfoParam;
import com.vo.param.ChangeParam;
import com.vo.param.ModifyPasswordParam;
import com.vo.param.ModifyUserInformationParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/center")
//@CrossOrigin
@AllArgsConstructor
public class CenterController {
    private final UserService userService;
    @GetMapping("personInformation")
    @UserLoginToken
    public R personInformation(@RequestAttribute(value="id") String id,@RequestBody String userNickname)
    { return userService.personInformation(id,userNickname); }
    @PostMapping("modifyUserInformation")
    @UserLoginToken
    public R modifyUserInformation(@RequestAttribute(value="id") String id,@RequestBody ModifyUserInformationParam modifyUserInformationParam)
    {

        return userService.modifyUserInformation(id,modifyUserInformationParam);
    }
    @PostMapping("modifyPassword")
    @UserLoginToken
    public R modifyPassword(@RequestAttribute(value="id") String id,@RequestBody ModifyPasswordParam modifyPasswordParam)
    {
        return  userService.modifyPassword(id,modifyPasswordParam);
    }
}
