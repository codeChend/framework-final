package com.startdt.modules.role.service;

import com.github.pagehelper.PageHelper;
import com.startdt.modules.common.utils.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@MapperScan("com.startdt.modules.role.dal")
@ComponentScan("com.startdt.modules.role")
@Slf4j
public class RoleStarterConfiguration {

    /**
     * 分页插件
     */
    @Bean
    @ConditionalOnMissingBean(PageHelper.class)
    public PageHelper paginationInterceptor() {
        log.info("加载分页插件");
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "false");
        props.setProperty("supportMethodsArguments", "false");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props);
        return pageHelper;
    }

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

}
