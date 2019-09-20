package com.startdt.modules.user.dal.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * desc
 *
 * @author xutianzhe
 * @Date 2019/7/11
 **/
@ApiModel(value="用户详情返回对象")
public class UserDetailVO {

    @ApiModelProperty(value = "主键id",name="id",example = "1")
    private Integer id;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号",name="userName")
    private String userName;

    /**
     * 昵称
     */
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
