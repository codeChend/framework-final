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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
