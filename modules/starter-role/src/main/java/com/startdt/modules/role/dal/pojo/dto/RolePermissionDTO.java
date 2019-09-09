package com.startdt.modules.role.dal.pojo.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/29 下午12:02
 * @Modified By:
 */
public class RolePermissionDTO implements Serializable {

    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限list
     */
    private List<PermissionCodeDTO> permissions;

    /**
     * code码
     */
    private String code;

    /**
     * 描述
     */
    private String note;

    /**
     * 角色启用状态：0->禁用；1->启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;

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

    public List<PermissionCodeDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionCodeDTO> permissions) {
        this.permissions = permissions;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }
}
