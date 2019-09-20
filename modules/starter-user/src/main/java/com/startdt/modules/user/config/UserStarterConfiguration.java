package com.startdt.modules.user.config;

import com.github.pagehelper.PageHelper;
import com.startdt.modules.user.controller.UserInfoController;
import com.startdt.modules.user.service.encode.PasswordEncode;
import com.startdt.modules.user.service.impl.TbUserInfoServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan("com.startdt.modules.user.dal")
public class UserStarterConfiguration {

    @Bean
    public PasswordEncode passwordEncode(){
        return new PasswordEncode();
    }

    @Bean
    public TbUserInfoServiceImpl getUserInfoService(){

        return new TbUserInfoServiceImpl();
    }

    @Bean
    public UserInfoController getUserInfoController(){

        return new UserInfoController();
    }

    /**
     * 分页插件
     */
    @Bean
    @ConditionalOnMissingBean(PageHelper.class)
    public PageHelper paginationInterceptor() {
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "false");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        return pageHelper;
    }

    @Bean
    public ReadModulesJsonConfig readModulesJsonConfig(){
        return new ReadModulesJsonConfig();
    }

}
