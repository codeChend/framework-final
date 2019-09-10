package com.startdt.modules.role.dal.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午4:57
 * @Modified By:
 */
@ApiModel(value =  "查询权限请求实体")
public class QueryPermissionDTO {
    /** 权限code **/
    @ApiModelProperty(value = "权限code",name = "code")
    private String code;

    /** 权限名称 **/
    @ApiModelProperty(value = "权限名称",name = "name")
    private String name;

    /** 类型，0 菜单，1 按钮，2 资源 **/
    @ApiModelProperty(value = "类型，0 菜单，1 按钮，2 资源",name = "type")
    private Byte type;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
