package com.startdt.modules.role.dal.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午4:20
 * @Modified By:
 */
@ApiModel(value = "权限节点实体")
public class PermissionNodeDTO {

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

    /** 权限名称 **/
    @ApiModelProperty(value = "权限值",name = "value")
    private String value;

    /** 路径 **/
    @ApiModelProperty(value = "路径",name = "resUrl")
    private String resUrl;

    /** 类型，0 菜单，1 按钮，2 资源 **/
    @ApiModelProperty(value = "类型，0 菜单，1 按钮，2 资源 ",name = "type",example = "0")
    private Byte type;

    /** 图标 **/
    @ApiModelProperty(value = "图标",name = "icon")
    private String icon;

    @ApiModelProperty(value = "子节点集",name = "permissionNodeSon")
    private List<PermissionNodeDTO> permissionNodeSon;

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

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
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

    public List<PermissionNodeDTO> getPermissionNodeSon() {
        return permissionNodeSon;
    }

    public void setPermissionNodeSon(List<PermissionNodeDTO> permissionNodeSon) {
        this.permissionNodeSon = permissionNodeSon;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
