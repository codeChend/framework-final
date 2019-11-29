package com.startdt.modules.role.dal.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author bo.chen
 * @Date 2019/11/28
 **/
@ApiModel(value = "权限节点实体带角色信息")
public class RolePermissionNodeDTO extends PermissionNodeDTO {
    @ApiModelProperty(value = "角色id",name = "roleId")
    private Integer roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "描述")
    private String note;

    // @ApiModelProperty(value = "平台code，区分不同平台或项目")
    // private Integer platformCode;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // public Integer getPlatformCode() {
    //     return platformCode;
    // }
    //
    // public void setPlatformCode(Integer platformCode) {
    //     this.platformCode = platformCode;
    // }
}
