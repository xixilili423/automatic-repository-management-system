package com.service;

import com.vo.R;
import com.vo.param.LoginParam;
import org.springframework.stereotype.Service;


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
     * 登陆
     * @param loginParam（用户名，密码）
     */
    R login(LoginParam loginParam);
}
