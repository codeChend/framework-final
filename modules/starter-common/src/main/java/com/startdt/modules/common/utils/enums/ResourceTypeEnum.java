package com.startdt.modules.common.utils.enums;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午5:42
 * @Modified By:
 */
public enum ResourceTypeEnum {
    ROLE(1,"角色"),

    PERMISSION(2,"权限");

    private Integer code;

    private String message;

    ResourceTypeEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
