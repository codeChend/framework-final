package com.startdt.modules.role.dal.pojo.request.role;

import com.startdt.modules.role.dal.pojo.dto.PermissionCodeDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午3:55
 * @Modified By:
 */
@ApiModel(value = "添加角色",description = "添加角色请求实体")
public class SaveRoleInfoReq {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "roleName",name = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    private String roleName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "note",name = "角色描述")
    private String note;

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
}
