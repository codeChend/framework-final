package com.startdt.modules.role.service;

import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfo;
import com.startdt.modules.role.dal.pojo.request.permission.PermissionReq;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.QueryPermissionDTO;

import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午2:19
 * @Modified By:
 */
public interface IResourcePermissionService {

    /**
     * 保存新的模块资源权限信息
     * @param permissionNodeDTOS
     * @return
     */
    int saveResourcePermission(PermissionNodeDTO permissionNodeDTOS);

    /**
     * 修改资源权限名称和图标
     * @param permissionReq
     * @return
     */
    int modifyResourcePermission(PermissionReq permissionReq);

    /**
     * 修改同等级资源的排序
     * @param permissionCodes
     * @return
     */
    int sortPermission(List<String> permissionCodes);

    /**
     * 会删除所有子节点
     * @param permissionCode
     * @return
     */
    int deletePermission(String permissionCode);

    /**
     * 根据条件查询权限资源列表
     * @param queryPermissionDTO
     * @return
     */
    List<PermissionNodeDTO> permissionNodeSelective(QueryPermissionDTO queryPermissionDTO);

    /**
     * 根据权限code批量获取权限列表
     * @param codes
     * @return
     */
    List<ResourcePermissionInfo> permissionInfoByCodes(List<String> codes);

    /**
     * 通过父节点获取权限信息
     * @param parentCode
     * @return
     */
    List<ResourcePermissionInfo> permissionInfoByParentCode(String parentCode);
}
