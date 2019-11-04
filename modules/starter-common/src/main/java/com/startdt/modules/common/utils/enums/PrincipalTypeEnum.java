package com.startdt.modules.common.utils.enums;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午5:38
 * @Modified By:
 */
public enum PrincipalTypeEnum {

    USER(1,"用户","授权主体是用户"),

    APP(2,"运用","授权主体是运用");

    private Integer code;

    private String message;

    private String note;

    private PrincipalTypeEnum(Integer code,String message,String note){
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
