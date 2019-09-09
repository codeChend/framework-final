package com.startdt.modules.common.utils.enums;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 上午10:21
 * @Modified By:
 */
public enum RolePermissionEnum {

    SYSTEM_PERMISSION(1,"系统权限","脚手架本身的权限code"),

    BUSINESS_PERMISSION(2,"业务权限","业务方定义的权限集code");

    private int code;

    private String message;

    private String note;

    RolePermissionEnum(int code,String message,String note){
        this.code = code;
        this.message = message;
        this.note = note;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getNote() {
        return note;
    }

}
