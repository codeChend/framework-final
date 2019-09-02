package com.startdt.modules.user.config;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.startdt.modules.common.pojo.ModulesTree;
import com.startdt.modules.common.utils.ModulesCache;
import com.startdt.modules.user.controller.UserInfoController;
import com.startdt.modules.user.service.encode.PasswordEncode;
import com.startdt.modules.user.service.impl.TbUserInfoServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@MapperScan("com.startdt.modules.user.dal")
public class UserStarterConfiguration {


    @Bean
    public PasswordEncode passwordEncode(){
        return new PasswordEncode();
    }

    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public TbUserInfoServiceImpl getUserInfoService(){

        return new TbUserInfoServiceImpl();
    }

    @Bean
    public UserInfoController getUserInfoController() throws IOException {
        //加载菜单静态文件
        InputStream inputStream = this.getClass().getResourceAsStream("/META-INF/starter-user-modules.json");
        ModulesTree modulesTree = JSONObject.parseObject(inputStream,ModulesTree.class);
        if(modulesTree!=null){
            ModulesCache.addModulesTree("user",modulesTree);
        }
        System.out.println("user启动器加载");
        return new UserInfoController();
    }

}
