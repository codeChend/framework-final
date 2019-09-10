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
@ApiModel(value = "PermissionNodeDTO",description = "权限节点实体")
public class PermissionNodeDTO {

    /** 权限code **/
    @ApiModelProperty(value = "code",name = "权限code")
    private String code;

    /** 权限code **/
    @ApiModelProperty(value = "code",name = "权限code")
    private String parentCode;

    /** 权限名称 **/
    @ApiModelProperty(value = "name",name = "权限名称")
    @NotEmpty(message = "权限名称不能为空")
    private String name;

    /** 路径 **/
    @ApiModelProperty(value = "resUrl",name = "路径")
    private String resUrl;

    /** 类型，0 菜单，1 按钮，2 资源 **/
    @ApiModelProperty(value = "type",name = "类型，0 菜单，1 按钮，2 资源 ",example = "0")
    private Byte type;

    /** 图标 **/
    @ApiModelProperty(value = "icon",name = "图标 ")
    private String icon;

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
}
