package com.startdt.modules.role.dal.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpMethod;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/29 下午12:00
 * @Modified By:
 */
public class UrlMethodDTO {

    @ApiModelProperty(value = "url统一采用*通配符，通配后续所有操作，？适配符：适配url路径参数",name = "url",example = "/api/starter/v1/users/?")
    private String url;

    /**
     * @see HttpMethod
     */
    @ApiModelProperty(value = "请求方法，GET POST DELETE PATCH等",name = "method",example = "GET")
    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
