package com.startdt.modules.role.service;


import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfo;
import com.startdt.modules.role.dal.pojo.dto.*;
import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import org.springframework.http.HttpMethod;

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
     * 禁用用户某个空间的某个角色
     * @param userId
     * @param roleId
     * @param spaceCode
     * @return
     */
    int deleteUserRole(Integer userId,Integer roleId,String spaceCode);

    /**
     * 获取用户所有的角色
     * @param example
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageResult<RoleInfoDTO> pageRoleByUserId(GrantPermissionExample example, int currentPage, int pageSize);

    /**
     * 通过用户id获取角色权限
     * @param userId
     * @return
     */
    List<RolePermissionDTO> listByUserId(Integer userId);

    /**
     * 通过用户id + spaceCode获取角色权限
     * @param userId
     * @param spaceCode
     * @return
     */
    List<RolePermissionDTO> listByUserId(Integer userId,String spaceCode);

    /**
     * 根据用户获取系统级的全部权限list
     * @param userId
     * @return
     */
    List<ResourcePermissionInfo> permissionAllByUserId(Integer userId,String spaceCode);

    /**
     * 通过userId获取所有菜单权限
     * @param userId
     * @return
     */
    List<PermissionNodeDTO> getMenuPermission(Integer userId,String spaceCode);

    /**
     * 通过userId获取功能权限值集合
     * @param userId
     * @return
     */
    List<String> getFunctionPermission(Integer userId,String spaceCode);

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
    List<UrlMethodDTO> getUrlPermission(Integer userId,String spaceCode);

    /**
     * 获取业务权限code集合
     * @param userId
     * @return
     */
    List<String> getBusinessPermission(Integer userId,String spaceCode);

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

    /**
     * 根据用户id判断当前接口是否有权限访问
     * @param userId        用户userId
     * @param url           接口请求url，采取/*和/?的适配方式
     * @param httpMethod    接口请求方法{@link HttpMethod}
     * @return
     */
    boolean checkAuth(Integer userId, String url, String httpMethod);
}
