package com.startdt.modules.role.dal.pojo.request.role;

import com.startdt.modules.role.dal.pojo.dto.PermissionCodeDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/9 下午3:55
 * @Modified By:
 */
public class SaveRoleInfoReq {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 描述
     */
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
