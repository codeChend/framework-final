package com.startdt.modules.common.utils.enums;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/30 上午11:52
 * @Modified By:
 */
public enum UrlMethodEnum {
    URL("url","路径"),

    METHOD("method","请求方法");

    private String code;

    private String message;

    UrlMethodEnum(String code,String message){
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

