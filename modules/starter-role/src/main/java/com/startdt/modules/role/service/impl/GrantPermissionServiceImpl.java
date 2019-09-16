package com.startdt.modules.role.service.impl;

import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.enums.PrincipalTypeEnum;
import com.startdt.modules.common.utils.enums.ResourceTypeEnum;
import com.startdt.modules.role.dal.mapper.GrantPermissionMapper;
import com.startdt.modules.role.dal.pojo.domain.GrantPermission;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfoExample;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IRolePermissionInfoService rolePermissionInfoService;

    @Override
    public int grantUserRole(GrantUserRoleReq grantUserRoleReq) {
        //角色是否重复授予


        //对应user赋予角色信息
        GrantPermission entity = new GrantPermission();
        entity.setPrincipalPart(grantUserRoleReq.getUserId());
        entity.setPrincipalPartType(PrincipalTypeEnum.USER.getCode().byteValue());
        entity.setResources(grantUserRoleReq.getRoleCode());
        entity.setResourcesType(ResourceTypeEnum.ROLE.getCode().byteValue());
        entity.setNote(grantUserRoleReq.getNote());

        return grantPermissionMapper.insertSelective(entity);
    }

    @Override
    public int deleteUserRole(String userId, String roleId) {
        GrantPermission entity = new GrantPermission();
        entity.setStatus((byte)0);

        GrantPermissionExample example = new GrantPermissionExample();
        example.or().andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue()).andPrincipalPartEqualTo(userId)
                .andResourcesEqualTo(roleId).andResourcesTypeEqualTo(ResourceTypeEnum.ROLE.getCode().byteValue());

        return grantPermissionMapper.updateByExampleSelective(entity,example);
    }

    @Override
    public Page<RoleInfoDTO> getRoleByUserId(GrantPermissionExample example, int currentPage, int pageSize) {
        if(currentPage <= 0) {
            currentPage = 1;
        }
        if(pageSize <= 0) {
            pageSize = 10;
        }
        if(example == null){
            example = new GrantPermissionExample();
        }

        long totalCount = grantPermissionMapper.countByExample(example);
        List<GrantPermission> dataList = grantPermissionMapper.selectByExamplePaging(example, (currentPage - 1) * pageSize, pageSize);
        List<String> roleIds =  dataList.parallelStream().map(GrantPermission::getResources).collect(Collectors.toList());;
        List<RoleInfoDTO> resultList = rolePermissionInfoService.listRole(roleIds);

        Page<RoleInfoDTO> pageObj=new Page<>();
        pageObj.setCurrentPage(currentPage);
        pageObj.setPageSize(pageSize);
        pageObj.setDataList(resultList);
        pageObj.setTotalCount(totalCount);
        pageObj.setTotalPage((int)Math.ceil(totalCount/(float)pageSize));

        return pageObj;
    }

    @Override
    public List<PermissionNodeDTO> getMenuPermission(String userId) {
        return null;
    }

    @Override
    public List<String> getUrlPermission(String userId) {
        return null;
    }

    @Override
    public List<String> getBussinessPermission(String userId) {
        return null;
    }


}
