package com.startdt.modules.role.dal.pojo.request.grant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午5:37
 * @Modified By:
 */
@ApiModel(value = "GrantUserRoleReq",description = "用户授予角色请求实体")
public class GrantUserRoleReq {

    @ApiModelProperty(value = "userId",name = "用户id")
    @NotEmpty(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty(value = "roleCode",name = "角色code")
    @NotEmpty(message = "角色code不能为空")
    private String roleCode;

    @ApiModelProperty(value = "note",name = "说明")
    private String note;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
