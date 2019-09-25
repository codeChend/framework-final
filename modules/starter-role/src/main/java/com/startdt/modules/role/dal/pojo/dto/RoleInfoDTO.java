package com.startdt.modules.role.dal.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午4:10
 * @Modified By:
 */
@ApiModel(value = "角色实体")
public class RoleInfoDTO {

    @ApiModelProperty(value = "角色id",name = "id")
    private Integer id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称",name = "roleName")
    private String roleName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述",name = "note")
    private String note;

    @ApiModelProperty(value = "创建时间",name = "gmtCreate")
    private Long gmtCreate;

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

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
