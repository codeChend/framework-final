package com.startdt.modules.role.dal.pojo.request.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午4:06
 * @Modified By:
 */
@ApiModel(value = "修改角色",description = "修改角色请求实体")
public class ModifyRoleInfoReq {

    @ApiModelProperty(value = "id",name = "角色id")
    @NotNull(message = "主键id不能为空")
    private Integer id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "roleName",name = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    private String roleName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "note",name = "描述")
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
