package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vo.R;
import com.vo.param.*;

//@Service
//public class UserService {
//
//    @Autowired
//    private Usermapper usermapper=(Usermapper)  ApplicationContextHelperUtil.getBean(Usermapper.class);
//    public boolean Login(user user1){
//        QueryWrapper<user> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("email",user1.getEmail()).eq("password",user1.getPassword());
//        return usermapper.equals(userQueryWrapper);
//    }
//}

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
    R changePassword(ChangeParam changeparam);
    R  registerManager(RegisterManagerParam registerManagerParam);
    /**
     * 4.主页信息获取
     * @get getInformation(token)
     * @return
     */
    R getInformation(String token);
    /**
     * 5.修改个人信息
     * @get getInformation(手机号，所属中转，token)
     * @return
     */
    R changeInformation(ChangeInfoParam changeInfoParam);

    User findUserById(String id);
    R managerHomePage(String id);
    R userHomePage(String id);
}
