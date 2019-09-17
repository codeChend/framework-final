package com.startdt.modules.common.utils.enums;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 上午10:30
 * @Modified By:
 */
public enum PermissionTypeEnum {
    MENU_PERMISSION(0,"菜单","一级菜单"),

    BUTTON_PERMISSION(1,"按钮","按钮权限");

//    RESOURCE_PERMISSION(2,"资源","资源权限");

    private Integer code;

    private String message;

    private String note;

    PermissionTypeEnum(Integer code,String message,String note){
        this.code = code;
        this.message = message;
        this.note = note;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getNote() {
        return note;
    }
}
