package com.startdt.modules.role.dal.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/17 下午8:46
 * @Modified By:
 */
public class PermissionDTO {
    /** 权限code **/
    @ApiModelProperty(value = "权限code",name = "code")
    private String code;

    /** 权限code **/
    @ApiModelProperty(value = "父类权限code",name = "parentCode")
    private String parentCode;

    /** 权限名称 **/
    @ApiModelProperty(value = "权限名称",name = "name")
    @NotEmpty(message = "权限名称不能为空")
    private String name;

    /** 权限值 **/
    @ApiModelProperty(value = "权限值，全局唯一且不能为空",name = "value")
    @NotEmpty(message = "权限值不能为空")
    private String value;

    @ApiModelProperty(value = "权限url与请求方法",name = "urlMethod")
    private UrlMethodDTO urlMethod;

    /** 类型，0 菜单，1 按钮，2 资源 **/
    @ApiModelProperty(value = "类型，0 菜单，1 按钮，2 资源 ",name = "type",example = "0")
    private Byte type;

    /** 图标 **/
    @ApiModelProperty(value = "图标",name = "icon")
    private String icon;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UrlMethodDTO getUrlMethod() {
        return urlMethod;
    }

    public void setUrlMethod(UrlMethodDTO urlMethod) {
        this.urlMethod = urlMethod;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "PermissionDTO{" +
                "code='" + code + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", urlMethod='" + urlMethod + '\'' +
                ", type=" + type +
                ", icon='" + icon + '\'' +
                '}';
    }
}
