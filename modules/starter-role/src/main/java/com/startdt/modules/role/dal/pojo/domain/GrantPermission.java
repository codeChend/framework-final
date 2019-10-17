package com.startdt.modules.role.dal.pojo.domain;

import java.util.Date;

public class GrantPermission {
    private Integer id;

    /** 主体 **/
    private String principalPart;

    /** 主体类型，1用户，2运用 **/
    private Byte principalPartType;

    /** 资源 **/
    private String resources;

    /** 资源类型，1角色，2权限 **/
    private Byte resourcesType;

    /** 空间code **/
    private String spaceCode;

    /** 描述 **/
    private String note;

    /** 授权是否开启：0->禁用；1->启用 **/
    private Byte status;

    /** 创建时间 **/
    private Date gmtCreate;

    /** 更新时间 **/
    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /** 主体 **/
    public String getPrincipalPart() {
        return principalPart;
    }

    /** 主体 **/
    public void setPrincipalPart(String principalPart) {
        this.principalPart = principalPart;
    }

    /** 主体类型，1用户，2运用 **/
    public Byte getPrincipalPartType() {
        return principalPartType;
    }

    /** 主体类型，1用户，2运用 **/
    public void setPrincipalPartType(Byte principalPartType) {
        this.principalPartType = principalPartType;
    }

    /** 资源 **/
    public String getResources() {
        return resources;
    }

    /** 资源 **/
    public void setResources(String resources) {
        this.resources = resources;
    }

    /** 资源类型，1角色，2权限 **/
    public Byte getResourcesType() {
        return resourcesType;
    }

    /** 资源类型，1角色，2权限 **/
    public void setResourcesType(Byte resourcesType) {
        this.resourcesType = resourcesType;
    }

    /** 描述 **/
    public String getNote() {
        return note;
    }

    /** 描述 **/
    public void setNote(String note) {
        this.note = note;
    }

    /** 授权是否开启：0->禁用；1->启用 **/
    public Byte getStatus() {
        return status;
    }

    /** 授权是否开启：0->禁用；1->启用 **/
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

    public String getSpaceCode() {
        return spaceCode;
    }

    public void setSpaceCode(String spaceCode) {
        this.spaceCode = spaceCode;
    }
}