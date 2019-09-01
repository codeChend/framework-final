package com.startdt.modules.login.service;

import com.startdt.modules.login.pojo.UserLoginVO;
import com.startdt.modules.login.service.JwtTokenUtil;
import org.springframework.context.annotation.Configuration;

public interface StarterLoginService {

    UserLoginVO login(String userName,String password);

}
