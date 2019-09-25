package com.startdt.modules.login.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/28 上午11:49
 * @Modified By:
 */
@Configuration
public class JwtConfig {
    /**
     * 安全秘钥
     */
    @Value("${starter.jwt.secret:framework-secret}")
    private String secret;

    @Value("${starter.jwt.expiration:7200}")
    private Long expiration;

    @Value("${starter.jwt.tokenHeader:STARTER_TOKEN}")
    private String tokenHeader;

    @Value("${starter.jwt.tokenHead:}")
    private String tokenHead;

    public String getSecret() {
        return secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public String getTokenHead() {
        return tokenHead;
    }
}
