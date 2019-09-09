package com.startdt.modules.role.dal.pojo.dto;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午4:52
 * @Modified By:
 */
public class PermissionDTO {
    /** 权限code **/
    private String code;

    /** 权限名称 **/
    private String name;

    /** 图标 **/
    private String icon;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
