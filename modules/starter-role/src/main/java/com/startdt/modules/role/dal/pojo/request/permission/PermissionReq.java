package com.startdt.modules.role.dal.pojo.request.permission;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午4:52
 * @Modified By:
 */
@ApiModel(value =  "权限修改请求实体")
public class PermissionReq {
    /** 权限code **/
    @ApiModelProperty(value = "权限code",name = "code")
    @NotEmpty(message = "权限code不为空")
    private String code;

    /** 权限名称 **/
    @ApiModelProperty(value = "权限名称",name = "name")
    private String name;

    /** 图标 **/
    @ApiModelProperty(value = "图标",name = "icon")
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
