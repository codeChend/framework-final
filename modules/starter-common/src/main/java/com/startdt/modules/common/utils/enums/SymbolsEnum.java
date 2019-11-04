package com.startdt.modules.common.utils.enums;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/29 下午5:36
 * @Modified By:
 */
public enum SymbolsEnum {

    ADAPTER("*","通配符"),

    WILDCARD("?","适配符");

    private String code;

    private String message;

    SymbolsEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
