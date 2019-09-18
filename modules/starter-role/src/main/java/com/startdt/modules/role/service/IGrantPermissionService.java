package com.startdt.modules.role.service;


import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfo;
import com.startdt.modules.role.dal.pojo.dto.PermissionAccessDTO;
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
    List<RoleInfoDTO> listByUserId(Integer userId);

    /**
     * 根据用户获取系统级的全部权限list
     * @param userId
     * @return
     */
    List<ResourcePermissionInfo> permissionAllByUserId(Integer userId);

    /**
     * 通过userId获取所有菜单权限
     * @param userId
     * @return
     */
    List<PermissionNodeDTO> getMenuPermission(Integer userId);

    /**
     * 通过角色id获取权限树进行授权
     * @param roleId
     * @return
     */
    List<PermissionAccessDTO> getRolePermission(Integer roleId);

    /**
     * 获取所有的url权限集
     * @param userId
     * @return
     */
    List<String> getUrlPermission(Integer userId);

    /**
     * 获取业务权限code集合
     * @param userId
     * @return
     */
    List<String> getBusinessPermission(Integer userId);

    /**
     * 给角色授权，若是子节点会附带上根节点的权限
     * @param roleId
     * @param permissionCode
     * @return
     */
    int grantRolePermission(Integer roleId,List<String> permissionCode);

    /**
     * 释放权限，若是父节点会释放所有子节点权限
     * @param roleId
     * @param permissionCode
     * @return
     */
    int releaseRolePermission(Integer roleId,List<String> permissionCode);
}
