package com.startdt.modules.role.service.impl;

import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

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


    @Override
    public int grantUserRole(GrantUserRoleReq grantUserRoleReq) {

        return 0;
    }

    @Override
    public int deletUserRole(String userId, String roleId) {
        return 0;
    }

    @Override
    public List<String> getRoleByUserId(String userId) {
        return null;
    }
}
