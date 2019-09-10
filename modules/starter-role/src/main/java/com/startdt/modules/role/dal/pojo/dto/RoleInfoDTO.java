package com.startdt.modules.role.dal.pojo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午4:10
 * @Modified By:
 */
@ApiModel(value = "RoleInfoDTO",description = "角色实体")
public class RoleInfoDTO {

    @ApiModelProperty(value = "id",name = "角色id")
    private Integer id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "roleName",name = "角色名称")
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
