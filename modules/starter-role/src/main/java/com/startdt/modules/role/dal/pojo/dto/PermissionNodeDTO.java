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
public class PermissionNodeDTO extends PermisionDTO{

    @ApiModelProperty(value = "子节点集",name = "permissionNodeSon")
    private List<PermissionNodeDTO> permissionNodeSon;

    public List<PermissionNodeDTO> getPermissionNodeSon() {
        return permissionNodeSon;
    }

    public void setPermissionNodeSon(List<PermissionNodeDTO> permissionNodeSon) {
        this.permissionNodeSon = permissionNodeSon;
    }
}
