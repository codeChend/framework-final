package com.startdt.modules.role.service.impl;

import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.role.dal.mapper.GrantPermissionMapper;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
@Configuration
public class GrantPermissionServiceImpl implements IGrantPermissionService {

    @Autowired
    private GrantPermissionMapper grantPermissionMapper;

    @Override
    public int grantUserRole(GrantUserRoleReq grantUserRoleReq) {

        return 0;
    }

    @Override
    public int deletUserRole(String userId, String roleId) {
        return 0;
    }

    @Override
    public Page<RoleInfoDTO> getRoleByUserId(GrantPermissionExample example, int currentPage, int pageSize) {
        return null;
    }

}
