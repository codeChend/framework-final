package com.startdt.modules.role.service.impl;

import com.startdt.modules.role.dal.pojo.dto.PermissionDTO;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.QueryPermissionDTO;
import com.startdt.modules.role.service.IResourcePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午3:02
 * @Modified By:
 */
@Service
public class ResourcePermissionServiceImpl implements IResourcePermissionService{
    @Override
    public int saveResourcePermission(PermissionNodeDTO permissionNodeDTOS) {
        return 0;
    }

    @Override
    public int modifyResourcePermission(PermissionDTO permissionDTO) {
        return 0;
    }

    @Override
    public int sortPermission(List<String> permissionCodes) {
        return 0;
    }

    @Override
    public int deletePermission(String permissionCode) {
        return 0;
    }

    @Override
    public List<PermissionNodeDTO> PermissionNodeSelective(QueryPermissionDTO queryPermissionDTO) {
        return null;
    }
}
