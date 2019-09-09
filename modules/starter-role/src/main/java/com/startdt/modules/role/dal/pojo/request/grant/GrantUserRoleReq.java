package com.startdt.modules.role.dal.pojo.request.grant;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午5:37
 * @Modified By:
 */
public class GrantUserRoleReq {

    private String userId;

    private String roleCode;

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
