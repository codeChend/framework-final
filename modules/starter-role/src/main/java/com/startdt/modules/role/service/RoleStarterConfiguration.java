package com.startdt.modules.role.service;

import com.startdt.modules.role.service.impl.GrantPermissionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleStarterConfiguration {


    @Bean
    public GrantPermissionServiceImpl init(){

        return new GrantPermissionServiceImpl();
    }
}
