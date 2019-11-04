package com.startdt.modules.login.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/3 下午8:02
 * @Modified By:
 */
@ApiModel(value="用户登录请求对象")
public class LoginReq {

    @ApiModelProperty(value = "用户名",name = "userName")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "用户密码",name = "passWord")
    @NotEmpty(message = "密码不能为空")
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
