package com.startdt.modules.role.service;

import com.startdt.modules.common.utils.page.PageResult;
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
    RoleInfoDTO insertRole(SaveRoleInfoReq saveRoleInfoReq);

    /**
     * 删除角色信息
     * @param id
     * @return
     */
    int deleteRole(Integer id);

    /**
     * 修改角色权限信息
     * @param roleInfo
     * @return
     */
    Integer editRole(ModifyRoleInfoReq roleInfo);

    /**
     * 分页获取角色列表
     * @param platformCode      平台code
     * @param currentPage       当前页
     * @param pageSize          每页大小
     * @returin
     */
    PageResult<RoleInfoDTO> pageRole(String platformCode, int currentPage, int pageSize);

    /**
     * 获取角色列表
     *
     * @param platformCode  平台code
     * @return
     */
    List<RoleInfoDTO> listRoles(String platformCode);

    /**
     * 通过角色名称获取角色信息
     * @param roleName
     * @param status
     * @return
     */
    RoleInfoDTO getRoleByName(String roleName,Integer status);

    /**
     * 通过角色id获取角色权限
     * @param id
     * @return
     */
    RolePermissionDTO getRoleById(Integer id);

    /**
     * 通过条件获取角色list
     * @param roleIds
     * @return
     */
    List<RolePermissionDTO> listRole(List<String> roleIds);

    /**
     * 角色添加授权
     * @param permissionDTO
     * @return
     */
    int modifyRolePermission(RolePermissionDTO permissionDTO);
}
