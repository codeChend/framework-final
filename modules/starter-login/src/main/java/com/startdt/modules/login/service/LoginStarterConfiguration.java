package com.startdt.modules.login.service;

import com.startdt.modules.login.service.impl.StarterLoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginStarterConfiguration {


    @Bean
    public StarterLoginServiceImpl init(){

        return new StarterLoginServiceImpl();
    }
}
