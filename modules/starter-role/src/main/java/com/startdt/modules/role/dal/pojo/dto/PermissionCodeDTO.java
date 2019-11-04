package com.startdt.modules.role.dal.pojo.dto;

import java.io.Serializable;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/9/9 上午10:18
 * @Modified By:
 */
public class PermissionCodeDTO implements Serializable{

    /**
     * 权限code
     */
    private String code;

    /**
     * 权限类型
     * @see com.startdt.modules.common.utils.enums.RolePermissionEnum
     */
    private Integer type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
