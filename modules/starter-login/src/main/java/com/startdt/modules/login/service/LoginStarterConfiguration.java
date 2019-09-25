package com.startdt.modules.login.service;

import com.startdt.modules.login.controller.LoginStarterController;
import com.startdt.modules.login.intercept.BackLoginInterceptor;
import com.startdt.modules.login.intercept.LoginMvcConfigure;
import com.startdt.modules.login.pojo.JwtConfig;
import com.startdt.modules.login.pojo.LoginUnFilter;
import com.startdt.modules.login.service.impl.StarterLoginServiceImpl;
import com.startdt.modules.user.service.ITbUserInfoService;
import com.startdt.modules.user.service.impl.TbUserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginStarterConfiguration {


    @Bean
    public StarterLoginServiceImpl starterLoginService(){

        return new StarterLoginServiceImpl();
    }

    @Bean
    public JwtConfig jwtConfig(){
        return new JwtConfig();
    }

    @Bean JwtTokenUtil jwtTokenUtil(){
        return new JwtTokenUtil();
    }

    @Bean
    public LoginUnFilter loginUnFilter(){
        return new LoginUnFilter();
    }

    @Bean
    public LoginStarterController loginStarterController(){
        return new LoginStarterController();
    }

    @Bean
    public BackLoginInterceptor backLoginInterceptor(){
        return new BackLoginInterceptor();
    }

    @Bean
    public LoginMvcConfigure loginMvcConfigure(){
        return new LoginMvcConfigure();
    }
}
