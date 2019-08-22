package com.startdt.modules.role.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleStarterConfiguration {


    @Bean
    public RoleServiceImpl init(){

        return new RoleServiceImpl();
    }
}
