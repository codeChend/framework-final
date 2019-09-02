package com.startdt.modules.login.service.impl;

import com.startdt.modules.login.pojo.UserLoginVO;
import com.startdt.modules.login.service.StarterLoginService;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/28 下午5:05
 * @Modified By:
 */
@Configuration
public class StarterLoginServiceImpl implements StarterLoginService{


    @Override
    public UserLoginVO login(String userName, String password) {
        //TODO 获取登录的用户信息和权限信息

        return null;
    }
}
