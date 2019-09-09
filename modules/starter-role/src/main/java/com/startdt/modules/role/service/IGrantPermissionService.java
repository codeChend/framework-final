package com.startdt.modules.role.service;


import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
public interface IGrantPermissionService {

    /**
     * 用户赋予某个角色
     * @param grantUserRoleReq
     * @return
     */
    int grantUserRole(GrantUserRoleReq grantUserRoleReq);

    /**
     * 禁用用户的某个角色
     * @param userId
     * @param roleId
     * @return
     */
    int deletUserRole(String userId,String roleId);

    /**
     * 获取用户所有的角色
     * @param userId
     * @return
     */
    List<String> getRoleByUserId(String userId);
}
