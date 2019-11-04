package com.startdt.modules.role.dal.pojo.request.grant;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author : chendong
 * @date : Create in 2019/11/4 下午8:14
 */
public class RoleIdsReq {
    @ApiModelProperty(value = "角色code",name = "roleCode",example = "2")
    @NotNull(message = "角色code不能为空")
    private Integer roleCode;

    @ApiModelProperty(value = "说明",name = "note")
    private String note;

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
