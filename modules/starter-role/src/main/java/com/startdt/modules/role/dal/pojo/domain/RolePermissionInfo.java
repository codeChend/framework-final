package com.startdt.modules.role.dal.pojo.domain;

import java.util.Date;

public class RolePermissionInfo {
    private Integer id;

    /** 角色名称 **/
    private String roleName;

    /** 描述 **/
    private String note;

    /** 角色启用状态：0->禁用；1->启用 **/
    private Byte status;

    /** 创建时间 **/
    private Date gmtCreate;

    /** 更新时间 **/
    private Date gmtModified;

    /** 权限list，json格式 **/
    private String permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 角色名称 **/
    public String getRoleName() {
        return roleName;
    }

    /** 角色名称 **/
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /** 描述 **/
    public String getNote() {
        return note;
    }

    /** 描述 **/
    public void setNote(String note) {
        this.note = note;
    }

    /** 角色启用状态：0->禁用；1->启用 **/
    public Byte getStatus() {
        return status;
    }

    /** 角色启用状态：0->禁用；1->启用 **/
    public void setStatus(Byte status) {
        this.status = status;
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

    /** 权限list，json格式 **/
    public String getPermission() {
        return permission;
    }

    /** 权限list，json格式 **/
    public void setPermission(String permission) {
        this.permission = permission;
    }
}