package com.startdt.modules.login.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserInfoCache implements Serializable{
    private Integer id;

    /** 账号 **/
    private String userName;

    /** 密码 **/
    private String password;

    /** 昵称 **/
    private String nickName;

    /** 电话号码 **/
    private String phone;

    /** 邮箱 **/
    private String email;

    /** 头像 **/
    private String icon;

    /** 描述 **/
    private String note;

    /** 帐号启用状态：0->禁用；1->启用 **/
    private Byte status;

    /** 最近登录时间 **/
    private Date loginTime;

    /** 创建时间 **/
    private Date gmtCreate;

    /** 更新时间 **/
    private Date gmtModified;

    /** 登录token **/
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 账号 **/
    public String getUserName() {
        return userName;
    }

    /** 账号 **/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 密码 **/
    public String getPassword() {
        return password;
    }

    /** 密码 **/
    public void setPassword(String password) {
        this.password = password;
    }

    /** 昵称 **/
    public String getNickName() {
        return nickName;
    }

    /** 昵称 **/
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /** 电话号码 **/
    public String getPhone() {
        return phone;
    }

    /** 电话号码 **/
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** 邮箱 **/
    public String getEmail() {
        return email;
    }

    /** 邮箱 **/
    public void setEmail(String email) {
        this.email = email;
    }

    /** 头像 **/
    public String getIcon() {
        return icon;
    }

    /** 头像 **/
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /** 描述 **/
    public String getNote() {
        return note;
    }

    /** 描述 **/
    public void setNote(String note) {
        this.note = note;
    }

    /** 帐号启用状态：0->禁用；1->启用 **/
    public Byte getStatus() {
        return status;
    }

    /** 帐号启用状态：0->禁用；1->启用 **/
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 最近登录时间 **/
    public Date getLoginTime() {
        return loginTime;
    }

    /** 最近登录时间 **/
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /** 创建时间 **/
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /** 创建时间 **/
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /** 更新时间 **/
    public Date getGmtModified() {
        return gmtModified;
    }

    /** 更新时间 **/
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}