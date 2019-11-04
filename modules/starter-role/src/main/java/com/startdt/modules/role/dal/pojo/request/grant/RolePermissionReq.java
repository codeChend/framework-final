package com.startdt.modules.role.dal.pojo.request.grant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/18 上午10:53
 * @Modified By:
 */
@ApiModel(value = "角色权限请求信息")
public class RolePermissionReq {

    @ApiModelProperty(value = "角色id",name = "roleId",example = "1")
    private Integer roleId;

    @ApiModelProperty(value = "权限集",name = "permissionCodes")
    private List<String> permissionCodes;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<String> getPermissionCodes() {
        return permissionCodes;
    }

    public void setPermissionCodes(List<String> permissionCodes) {
        this.permissionCodes = permissionCodes;
    }
}
