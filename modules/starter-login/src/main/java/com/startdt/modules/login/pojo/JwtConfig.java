package com.startdt.modules.login.pojo;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/28 上午11:49
 * @Modified By:
 */
public class JwtConfig {
    /**
     * 安全秘钥
     */
    private String secret = "framework-secret";

    /**
     * 过期秒数2小时*3600
     */
    private Long expiration = 7200L;

    /**
     * http head
     */
    private String tokenHeader = "STARTER_TOKEN";

    /**
     * 前缀标识
     */
    private String tokenHead = "Starter";

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }
}
