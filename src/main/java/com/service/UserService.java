package com.service;

import org.springframework.stereotype.Service;
import com.vo.R;
import com.vo.param.LoginParam;
import com.vo.param.RegisterParam;
import com.vo.param.InitStockParm;

@Service
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

public interface UserService {
    /**
     * 1.登陆
     * @param loginParam（用户名,密码）
     * @return
     */
    R login(LoginParam loginParam);

    /**
     * 2.注册
     * @param registerParam(用户名,密码,确认密码）,返回码)
     * @return
     */
    R register(RegisterParam registerParam);

    /**
     * 3.仓库初始化 initStock(x,y,小车数,闸机数，用户名)
     * @return
     */
    R initStock(InitStockParm initStockParm);

}
