package com.startdt.modules.user.service;

import com.startdt.modules.user.service.impl.TbUserInfoServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.startdt.modules.user.dal")
public class UserStarterConfiguration {


    @Bean
    public TbUserInfoServiceImpl init(){

        return new TbUserInfoServiceImpl();
    }
}
