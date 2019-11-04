package com.startdt.modules.login.intercept;

import com.alibaba.fastjson.JSON;
import com.startdt.modules.login.pojo.JwtConfig;
import com.startdt.modules.login.pojo.LoginUnFilter;
import com.startdt.modules.login.service.JwtTokenUtil;
import com.startdt.modules.user.service.ITbUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/25 下午2:44
 * @Modified By:
 */
@Configuration
@Order(1)
@Slf4j
public class LoginMvcConfigure extends WebMvcConfigurerAdapter {

    @Autowired
    private BackLoginInterceptor backLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(backLoginInterceptor);
    }
}
