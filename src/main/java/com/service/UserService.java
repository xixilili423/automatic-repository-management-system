package com.service;

import com.annotation.UserLoginToken;
import com.baomidou.mybatisplus.extension.service.IService;
import com.entity.User;
import com.vo.R;
import com.vo.param.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService extends IService<User> {
    /**
     * 1.登陆
     *
     * @param loginParam（token,用户名,密码）
     * @return
     */
    R login(LoginParam loginParam);

    /**
     * 2.注册
     * @param registerParam(用户名,密码）
     * @return
     */
    R register(RegisterParam registerParam);
    /**
     * 3.改密码
     * @get getInformation(旧密码，新密码，token)
     * @return
     */
   R searchParce(String id, String userNickname);
   R modifyUserInformation(String id,ModifyUserInformationParam modifyUserInformationParam);

   R modifyPassword(String id, ModifyPasswordParam modifyPasswordParam);

    R  registerManager(RegisterManagerParam registerManagerParam);
    /**
     * 4.主页信息获取
     * @get getInformation(token)
     * @return
     */
    /**
     * 5.修改个人信息
     * @get getInformation(手机号，所属中转，token)
     * @return
     */

    User findUserById(String id);
    R managerHomePage(String id);
    R userHomePage(String id);
}
