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
 * 服务类
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
public interface IGrantPermissionService {

    /**
     * 用户赋予某个角色
     *
     * @param grantUserRoleReq
     * @return
     */
    int grantUserRole(GrantUserRoleReq grantUserRoleReq);

    /**
     * 用户批量绑定角色
     *
     * @param userId    用户id
     * @param roleIds   角色id列表
     * @param spaceCode 空间id
     * @return int
     * @author weilong
     * @since 2019/10/21 下午5:44
     */
    int grantUserRoleBatch(String userId, List<String> roleIds, String spaceCode);

    /**
     * 禁用用户的某个角色
     *
     * @param userId
     * @param roleId
     * @return
     */
    int deleteUserRole(String userId, Integer roleId);

    /**
     * 禁用用户某个空间的某个角色
     *
     * @param userId
     * @param roleId
     * @param spaceCode
     * @return
     */
    int deleteUserRole(String userId, Integer roleId, String spaceCode);

    /**
     * 批量禁用某个空间的用户角色
     *
     * @param userId    用户id
     * @param spaceCode 空间id
     * @return int
     * @author weilong
     * @since 2019/10/25 下午4:59
     */
    int deleteUserRoleBatch(List<String> userId, String spaceCode);

    /**
     * 获取用户所有的角色
     *
     * @param userId      用户账号id
     * @param spaceCode   项目code
     * @param currentPage 当前页
     * @param pageSize    每页大小
     * @return
     */
    PageResult<RoleInfoDTO> pageRoleByUserId(String userId, String spaceCode, int currentPage, int pageSize);

    /**
     * 通过用户id获取角色权限
     *
     * @param userId
     * @return
     */
    List<RolePermissionDTO> listByUserId(String userId);

    /**
     * 通过用户id + spaceCode获取角色权限
     *
     * @param userId
     * @param spaceCode
     * @return
     */
    List<RolePermissionDTO> listByUserId(String userId, String spaceCode);

    /**
     * 通过用户id + spaceCode获取角色权限
     *
     * @param userId
     * @param spaceCode
     * @return
     */
    List<RolePermissionDTO> listByUserId(String userId, List<String> spaceCode);

    /**
     * 根据用户获取系统级的全部权限list
     *
     * @param userId
     * @return
     */
    List<ResourcePermissionInfo> permissionAllByUserId(String userId, String spaceCode);

    /**
     * 通过userId获取所有菜单权限
     *
     * @param userId    用户账号id
     * @param spaceCode 项目code
     * @return
     */
    List<PermissionNodeDTO> getMenuPermission(String userId, List<String> spaceCode);

    /**
     * 通过userId获取所有菜单权限和角色信息
     *
     * @param userId
     * @param spaceCode
     * @return
     */
    public List<RolePermissionNodeDTO> getMenuPermissionWithRole(String userId, List<String> spaceCode);

    /**
     * 通过userId获取功能权限值集合
     *
     * @param userId
     * @return
     */
    List<String> getFunctionPermission(String userId, String spaceCode);

    /**
     * 通过角色id获取权限树进行授权
     *
     * @param roleId
     * @return
     */
    List<PermissionAccessDTO> getRolePermission(Integer roleId);

    /**
     * 获取所有的url权限集
     *
     * @param userId
     * @return
     */
    List<UrlMethodDTO> getUrlPermission(String userId, String spaceCode);

    /**
     * 获取业务权限code集合
     *
     * @param userId
     * @return
     */
    List<String> getBusinessPermission(String userId, String spaceCode);

    /**
     * 给角色授权，若是子节点会附带上根节点的权限
     *
     * @param roleId
     * @param permissionCode
     * @return
     */
    int grantRolePermission(Integer roleId, List<String> permissionCode);

    /**
     * 释放权限，若是父节点会释放所有子节点权限
     *
     * @param roleId
     * @param permissionCode
     * @return
     */
    int releaseRolePermission(Integer roleId, List<String> permissionCode);

    /**
     * 根据用户id判断当前接口是否有权限访问
     *
     * @param userId     用户userId
     * @param url        接口请求url，采取/*和/?的适配方式
     * @param httpMethod 接口请求方法{@link HttpMethod}
     * @return
     */
    boolean checkAuth(String userId, String url, String httpMethod);

    /**
     * 角色id分层捞出所有权限集合
     *
     * @param roleId 角色id
     * @return java.util.List<com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO>
     * @author weilong
     * @since 2019/10/21 下午6:13
     */
    List<PermissionNodeDTO> getPermissionByRole(Integer roleId);

}
