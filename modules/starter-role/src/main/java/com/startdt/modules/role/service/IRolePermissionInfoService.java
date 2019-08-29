package com.startdt.modules.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
public interface IRolePermissionInfoService extends IService<RolePermissionInfo> {

    RolePermissionInfo insertRolePermission();

}
