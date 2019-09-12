package com.startdt.modules.role.dal.pojo.domain;

import java.util.Date;

public class ResourcePermissionInfo {
    private Integer id;

    /** 权限ccode **/
    private String code;

    /** 权限名称 **/
    private String name;

    /** 父权限code **/
    private String parentCode;

    /** 权限值 **/
    private String value;

    /** 路径 **/
    private String resUrl;

    /** 类型，0 菜单，1 按钮，2 资源 **/
    private Byte type;

    /** 排序 **/
    private Integer sort;

    /** 图标 **/
    private String icon;

    /** 是否删除，0否，1删除 **/
    private Byte isDelete;

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

    /** 权限ccode **/
    public String getCode() {
        return code;
    }

    /** 权限ccode **/
    public void setCode(String code) {
        this.code = code;
    }

    /** 权限名称 **/
    public String getName() {
        return name;
    }

    /** 权限名称 **/
    public void setName(String name) {
        this.name = name;
    }

    /** 父权限code **/
    public String getParentCode() {
        return parentCode;
    }

    /** 父权限code **/
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /** 权限值 **/
    public String getValue() {
        return value;
    }

    /** 权限值 **/
    public void setValue(String value) {
        this.value = value;
    }

    /** 路径 **/
    public String getResUrl() {
        return resUrl;
    }

    /** 路径 **/
    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    /** 类型，0 菜单，1 按钮，2 资源 **/
    public Byte getType() {
        return type;
    }

    /** 类型，0 菜单，1 按钮，2 资源 **/
    public void setType(Byte type) {
        this.type = type;
    }

    /** 排序 **/
    public Integer getSort() {
        return sort;
    }

    /** 排序 **/
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /** 图标 **/
    public String getIcon() {
        return icon;
    }

    /** 图标 **/
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /** 是否删除，0否，1删除 **/
    public Byte getIsDelete() {
        return isDelete;
    }

    /** 是否删除，0否，1删除 **/
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
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
}