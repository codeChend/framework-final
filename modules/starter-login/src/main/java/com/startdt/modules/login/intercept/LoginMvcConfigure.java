package com.startdt.modules.login.intercept;

import com.startdt.modules.login.pojo.JwtConfig;
import com.startdt.modules.login.pojo.LoginUnFilter;
import com.startdt.modules.login.service.JwtTokenUtil;
import com.startdt.modules.user.service.ITbUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/25 下午2:44
 * @Modified By:
 */
@Configuration
@Order(1)
public class LoginMvcConfigure extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginUnFilter loginUnFilter;

    @Autowired
    private ITbUserInfoService userInfoService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        JwtConfig jwtConfig = new JwtConfig();

        registry.addInterceptor(new BackLoginInterceptor(userInfoService,jwtConfig, loginUnFilter));
    }
}
