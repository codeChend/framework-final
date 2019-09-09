package com.startdt.modules.role.service;

import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfoExample;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
import com.startdt.modules.role.dal.pojo.dto.RolePermissionDTO;
import com.startdt.modules.role.dal.pojo.request.role.ModifyRoleInfoReq;
import com.startdt.modules.role.dal.pojo.request.role.SaveRoleInfoReq;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
public interface IRolePermissionInfoService{

    /**
     * 新增角色权限
     * @param saveRoleInfoReq
     * @return
     */
    Result<Integer> insertRolePermission(SaveRoleInfoReq saveRoleInfoReq);

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
    Result<Integer> editRole(ModifyRoleInfoReq roleInfo);

    /**
     * 获取角色列表
     * @returin
     */
    Result<List<RoleInfoDTO>> listRole(RolePermissionInfoExample rolePermissionInfoExample);


    /**
     * 通过角色名称获取角色信息
     * @param roleName
     * @param status
     * @return
     */
    Result<RoleInfoDTO> getRoleByName(String roleName,Integer status);

    /**
     * 通过角色id获取角色权限
     * @param id
     * @return
     */
    Result<RolePermissionDTO> getRoleById(Integer id);

    /**
     * 通过userId获取所有菜单权限
     * @param userId
     * @return
     */
    Result<List<PermissionNodeDTO>> getMenuPermission(String userId);

    /**
     * 获取所有的接口权限
     * @param userId
     * @return
     */
    Result<List<String>> getUrlPermission(String userId);

    /**
     * 获取业务权限code集合
     * @param userId
     * @return
     */
    Result<List<String>> getBussinessPermission(String userId);

}
