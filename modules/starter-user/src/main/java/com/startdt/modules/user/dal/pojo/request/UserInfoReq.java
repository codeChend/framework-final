package com.startdt.modules.user.dal.pojo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/23 下午3:21
 * @Modified By:
 */
@ApiModel(value="用户基础请求实体")
public class UserInfoReq {
    /**
     * 账号
     */
    @NotEmpty(message="账号不能为空")
    @ApiModelProperty(value = "账号",name="userName")
    private String userName;

    /**
     * 密码
     */
    @NotEmpty(message="密码不能为空")
    @ApiModelProperty(value = "密码",name="password")
    private String password;

    /**
     * 昵称
     */
    @NotEmpty(message="昵称不能为空")
    @ApiModelProperty(value = "昵称",name="nickName")
    private String nickName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话",name="phone")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱",name="email")
    private String email;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像",name="icon")
    private String icon;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述",name="note")
    private String note;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
