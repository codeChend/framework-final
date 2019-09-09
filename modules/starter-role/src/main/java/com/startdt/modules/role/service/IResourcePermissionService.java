package com.startdt.modules.role.service;

import com.startdt.modules.role.dal.pojo.dto.PermissionDTO;
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
    int saveResourPermission(PermissionNodeDTO permissionNodeDTOS);

    /**
     * 修改资源权限名称和图标
     * @param permissionDTO
     * @return
     */
    int modifyResourPermission(PermissionDTO permissionDTO);

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
    List<PermissionNodeDTO> PermissionNodeSelective(QueryPermissionDTO queryPermissionDTO);
}
