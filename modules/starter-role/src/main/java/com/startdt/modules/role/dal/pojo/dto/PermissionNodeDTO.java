package com.startdt.modules.role.dal.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午4:20
 * @Modified By:
 */
@ApiModel(value = "权限节点实体")
public class PermissionNodeDTO extends PermissionDTO {

    @ApiModelProperty(value = "子节点集",name = "permissionNodeSon")
    private List<PermissionNodeDTO> permissionNodeSon;

    public List<PermissionNodeDTO> getPermissionNodeSon() {
        return permissionNodeSon;
    }

    public void setPermissionNodeSon(List<PermissionNodeDTO> permissionNodeSon) {
        this.permissionNodeSon = permissionNodeSon;
    }
}
