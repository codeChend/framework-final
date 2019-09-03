package com.startdt.modules.user.config;

import com.startdt.modules.user.controller.UserInfoController;
import com.startdt.modules.user.service.encode.PasswordEncode;
import com.startdt.modules.user.service.impl.TbUserInfoServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.startdt.modules.user.dal")
public class UserStarterConfiguration {

    @Bean
    public PasswordEncode passwordEncode(){
        return new PasswordEncode();
    }

    @Bean
    public TbUserInfoServiceImpl getUserInfoService(){

        return new TbUserInfoServiceImpl();
    }

    @Bean
    public UserInfoController getUserInfoController(){

        return new UserInfoController();
    }

    @Bean
    public ReadModulesJsonConfig readModulesJsonConfig(){
        return new ReadModulesJsonConfig();
    }

}
