package com.startdt.modules.login.pojo;

import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/8/28 下午4:55
 * @Modified By:
 */
public class UserLoginVO {
    private TbUserInfo userInfo;

    private String token;

    public TbUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TbUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
