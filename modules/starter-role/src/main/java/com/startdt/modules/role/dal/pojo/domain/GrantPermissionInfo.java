package com.startdt.modules.role.dal.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
@TableName("grant_permission_info")
public class GrantPermissionInfo extends Model<GrantPermissionInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 主体
     */
    private String principalPart;

    /**
     * 主体类型，1用户，2运用
     */
    private Integer principalPartType;

    /**
     * 资源
     */
    private String resources;

    /**
     * 资源类型，1角色，2权限
     */
    private Integer resourcesTypy;

    /**
     * 描述
     */
    private String note;

    /**
     * 授权是否开启：0->禁用；1->启用
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
    public String getPrincipalPart() {
        return principalPart;
    }

    public void setPrincipalPart(String principalPart) {
        this.principalPart = principalPart;
    }
    public Integer getPrincipalPartType() {
        return principalPartType;
    }

    public void setPrincipalPartType(Integer principalPartType) {
        this.principalPartType = principalPartType;
    }
    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
    public Integer getResourcesTypy() {
        return resourcesTypy;
    }

    public void setResourcesTypy(Integer resourcesTypy) {
        this.resourcesTypy = resourcesTypy;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "GrantPermissionInfo{" +
        "id=" + id +
        ", principalPart=" + principalPart +
        ", principalPartType=" + principalPartType +
        ", resources=" + resources +
        ", resourcesTypy=" + resourcesTypy +
        ", note=" + note +
        ", status=" + status +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
