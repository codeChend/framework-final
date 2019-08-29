package com.startdt.modules.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdt.modules.role.dal.mapper.RolePermissionInfoMapper;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
@Service
public class RolePermissionInfoServiceImpl extends ServiceImpl<RolePermissionInfoMapper, RolePermissionInfo> implements IRolePermissionInfoService {

    @Override
    public RolePermissionInfo insertRolePermission() {
        return null;
    }
}
