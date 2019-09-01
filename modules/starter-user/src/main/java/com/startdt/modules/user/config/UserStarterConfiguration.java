package com.startdt.modules.user.config;

import com.alibaba.fastjson.JSONObject;
import com.startdt.modules.common.pojo.ModulesTree;
import com.startdt.modules.common.utils.ModulesCache;
import com.startdt.modules.user.controller.UserInfoController;
import com.startdt.modules.user.service.impl.TbUserInfoServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@MapperScan(basePackages = "com.startdt.modules.user.dal")
public class UserStarterConfiguration {


    @Bean
    public TbUserInfoServiceImpl getUserInfoService(){

        return new TbUserInfoServiceImpl();
    }

    @Bean
    public UserInfoController getUserInfoController(){
        return new UserInfoController();
    }

    @Bean
    public void saveModulesToCache() throws IOException {
        //加载菜单静态文件
        InputStream inputStream = this.getClass().getResourceAsStream("/META-INF/starter-user-modules.json");
        ModulesTree modulesTree = JSONObject.parseObject(inputStream,ModulesTree.class);
        if(modulesTree!=null){
            ModulesCache.addModulesTree(modulesTree);
        }
    }

}
