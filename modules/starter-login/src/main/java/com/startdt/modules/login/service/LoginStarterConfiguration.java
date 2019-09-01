package com.startdt.modules.login.service;

import com.startdt.modules.login.intercept.BackLoginInterceptor;
import com.startdt.modules.login.pojo.JwtConfig;
import com.startdt.modules.login.service.impl.StarterLoginServiceImpl;
import com.startdt.modules.user.service.ITbUserInfoService;
import com.startdt.modules.user.service.impl.TbUserInfoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginStarterConfiguration {


    @Bean
    public StarterLoginServiceImpl starterLoginService(){

        return new StarterLoginServiceImpl();
    }

    @Bean
    public BackLoginInterceptor backLoginInterceptor(){
        ITbUserInfoService tbUserInfoService = new TbUserInfoServiceImpl();
        JwtConfig jwtConfig = new JwtConfig();
        return new BackLoginInterceptor(tbUserInfoService,jwtConfig);
    }
}
