package com.startdt.modules.role.dal.pojo.dto;

import java.util.Date;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午4:20
 * @Modified By:
 */
public class PermissionNodeDTO {

    /** 权限code **/
    private String code;

    /** 权限code **/
    private String parentCode;

    /** 权限名称 **/
    private String name;

    /** 路径 **/
    private String resUrl;

    /** 类型，0 菜单，1 按钮，2 资源 **/
    private Byte type;

    /** 排序 **/
    private Integer sort;

    /** 图标 **/
    private String icon;

    private List<PermissionNodeDTO> permissionNodeSon;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<PermissionNodeDTO> getPermissionNodeSon() {
        return permissionNodeSon;
    }

    public void setPermissionNodeSon(List<PermissionNodeDTO> permissionNodeSon) {
        this.permissionNodeSon = permissionNodeSon;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
