package com.startdt.modules.role.service;

import com.startdt.modules.role.controller.GrantPermissionController;
import com.startdt.modules.role.controller.ResourcePermissionController;
import com.startdt.modules.role.controller.RolePermissionController;
import com.startdt.modules.role.service.impl.GrantPermissionServiceImpl;
import com.startdt.modules.role.service.impl.ResourcePermissionServiceImpl;
import com.startdt.modules.role.service.impl.RolePermissionInfoServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.startdt.modules.role.dal")
public class RoleStarterConfiguration {


    @Bean
    public GrantPermissionServiceImpl grantPermissionService(){

        return new GrantPermissionServiceImpl();
    }

    @Bean
    public ResourcePermissionServiceImpl resourcePermissionService(){
        return new ResourcePermissionServiceImpl();
    }

    @Bean
    public RolePermissionInfoServiceImpl rolePermissionInfoService(){
        return new RolePermissionInfoServiceImpl();
    }

    @Bean
    public GrantPermissionController grantPermissionController(){
        return new GrantPermissionController();
    }

    @Bean
    public ResourcePermissionController resourcePermissionController(){
        return new ResourcePermissionController();
    }

    @Bean
    public RolePermissionController rolePermissionController(){
        return new RolePermissionController();
    }
}
