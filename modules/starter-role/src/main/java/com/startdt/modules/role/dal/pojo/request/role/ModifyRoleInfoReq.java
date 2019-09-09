package com.startdt.modules.role.dal.pojo.request.role;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午4:06
 * @Modified By:
 */
public class ModifyRoleInfoReq {

    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
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
