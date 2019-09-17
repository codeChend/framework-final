package com.startdt.modules.role.dal.pojo.dto;

import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/17 下午8:36
 * @Modified By:
 */
public class PermissionAccessDTO extends PermisionDTO{

    private Boolean isAccess;

    private List<PermissionAccessDTO> permissionAccessSon;

    public PermissionAccessDTO(){
        this.isAccess = false;
    }

    public Boolean getAccess() {
        return isAccess;
    }

    public void setAccess(Boolean access) {
        isAccess = access;
    }

    public List<PermissionAccessDTO> getPermissionAccessSon() {
        return permissionAccessSon;
    }

    public void setPermissionAccessSon(List<PermissionAccessDTO> permissionAccessSon) {
        this.permissionAccessSon = permissionAccessSon;
    }
}
