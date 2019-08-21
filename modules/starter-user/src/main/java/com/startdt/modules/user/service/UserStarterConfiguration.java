package com.startdt.modules.user.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserStarterConfiguration {


    @Bean
    public UserServiceImpl init(){

        return new UserServiceImpl();
    }
}
