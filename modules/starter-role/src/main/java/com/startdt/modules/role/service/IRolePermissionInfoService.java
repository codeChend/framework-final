package com.startdt.modules.role.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.dal.pojo.dto.RolePermissionDTO;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
public interface IRolePermissionInfoService extends IService<RolePermissionInfo> {

    /**
     * 新增角色权限
     * @param rolePermissionDTO
     * @return
     */
    Result<RolePermissionInfo> insertRolePermission(RolePermissionDTO rolePermissionDTO);

    /**
     * 删除角色权限信息
     * @param id
     * @return
     */
    Result<Integer> deleteRolePermission(Integer id);

    /**
     * 修改角色权限信息
     * @param roleInfo
     * @return
     */
    Result<RolePermissionInfo> editRole(RolePermissionInfo roleInfo);

    /**
     * 通过角色id获取角色权限
     * @param id
     * @return
     */
    Result<RolePermissionInfo> getRoleById(Integer id);

    /**
     * 获取角色列表
     * @param queryWrapper
     * @return
     */
    Result<List<RolePermissionInfo>> listRole(QueryWrapper<RolePermissionInfo> queryWrapper);


    /**
     * 通过角色名称获取角色信息
     * @param roleName
     * @param status
     * @return
     */
    Result<RolePermissionInfo> getRoleByName(String roleName,Integer status);
}
