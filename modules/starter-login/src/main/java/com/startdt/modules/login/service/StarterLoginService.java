package com.startdt.modules.login.service;

import com.startdt.modules.login.pojo.UserLoginVO;

public interface StarterLoginService {

    UserLoginVO login(String userName,String password);

}
