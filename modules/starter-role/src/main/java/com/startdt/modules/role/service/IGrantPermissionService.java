package com.startdt.modules.role.service;


import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
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
    int deleteUserRole(Integer userId,Integer roleId);

    /**
     * 获取用户所有的角色
     * @param example
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<RoleInfoDTO> pageRoleByUserId(GrantPermissionExample example, int currentPage, int pageSize);

    /**
     * 通过用户id获取角色列表
     * @param userId
     * @return
     */
    List<RoleInfoDTO> listByUserId(String userId);

    /**
     * 通过userId获取所有菜单权限
     * @param userId
     * @return
     */
    List<PermissionNodeDTO> getMenuPermission(String userId);

    /**
     * 获取所有的url权限集
     * @param userId
     * @return
     */
    List<String> getUrlPermission(String userId);

    /**
     * 获取业务权限code集合
     * @param userId
     * @return
     */
    List<String> getBusinessPermission(String userId);
}
