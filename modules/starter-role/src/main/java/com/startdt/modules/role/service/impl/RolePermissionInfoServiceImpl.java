package com.startdt.modules.role.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.role.dal.mapper.RolePermissionInfoMapper;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfoExample;
import com.startdt.modules.role.dal.pojo.dto.PermissionCodeDTO;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
import com.startdt.modules.role.dal.pojo.dto.RolePermissionDTO;
import com.startdt.modules.role.dal.pojo.request.role.ModifyRoleInfoReq;
import com.startdt.modules.role.dal.pojo.request.role.SaveRoleInfoReq;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

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
public class RolePermissionInfoServiceImpl implements IRolePermissionInfoService {

    @Autowired
    private RolePermissionInfoMapper rolePermissionInfoMapper;

    @Override
    public int insertRole(SaveRoleInfoReq rolePermissionDTO) {
        RolePermissionInfo rolePermissionInfo = BeanConverter.convert(rolePermissionDTO,RolePermissionInfo.class);

        return rolePermissionInfoMapper.insertSelective(rolePermissionInfo);
    }

    @Override
    public int deleteRole(Integer id) {
        RolePermissionInfo rolePermissionInfo = new RolePermissionInfo();
        rolePermissionInfo.setId(id);
        rolePermissionInfo.setStatus((byte)0);

        return rolePermissionInfoMapper.updateByPrimaryKeySelective(rolePermissionInfo);
    }

    @Override
    public Integer editRole(ModifyRoleInfoReq roleInfo) {
        RolePermissionInfo rolePermissionInfo = BeanConverter.convert(roleInfo,RolePermissionInfo.class);
        Integer save = 0;
        if (roleInfo.getId() != null) {
            save = rolePermissionInfoMapper.updateByPrimaryKeySelective(rolePermissionInfo);
        }else {
            throw new FrameworkException(BizResultConstant.ID_BLANK);
        }
        return save;
    }

    @Override
    public Page<RoleInfoDTO> listRole(RolePermissionInfoExample example,int currentPage,int pageSize) {
        if(currentPage <= 0) {
            currentPage = 1;
        }
        if(pageSize <= 0) {
            pageSize = 10;
        }
        long totalCount = rolePermissionInfoMapper.countByExample(example);
        List<RolePermissionInfo> dataList = rolePermissionInfoMapper.selectByExamplePaging(example, (currentPage - 1) * pageSize, pageSize);
        List<RoleInfoDTO> resultList = BeanConverter.mapList(dataList,RoleInfoDTO.class);

        Page<RoleInfoDTO> pageObj=new Page<>();
        pageObj.setCurrentPage(currentPage);
        pageObj.setPageSize(pageSize);
        pageObj.setDataList(resultList);
        pageObj.setTotalCount(totalCount);
        pageObj.setTotalPage((int)Math.ceil(totalCount/(float)pageSize));
        return pageObj;
    }

    @Override
    public RoleInfoDTO getRoleByName(String roleName, Integer status) {
        RolePermissionInfoExample example = new RolePermissionInfoExample();
        example.or().andRoleNameEqualTo(roleName).andStatusEqualTo(status.byteValue());
        RolePermissionInfo rolePermissionInfo = rolePermissionInfoMapper.selectOneByExample(example);

        return BeanConverter.convert(rolePermissionInfo,RoleInfoDTO.class);
    }

    @Override
    public RolePermissionDTO getRoleById(Integer id) {
        RolePermissionInfo rolePermissionInfo = rolePermissionInfoMapper.selectByPrimaryKey(id);
        if(rolePermissionInfo == null){
            throw new FrameworkException(BizResultConstant.NO_CONTENT_DATA);
        }
        RolePermissionDTO rolePermissionDTO = BeanConverter.convert(rolePermissionInfo,RolePermissionDTO.class);
        if(!StringUtils.isEmpty(rolePermissionInfo.getPermission())){
            List<PermissionCodeDTO> permissionCodeDTO = JSONArray.parseArray(rolePermissionInfo.getPermission(),PermissionCodeDTO.class);
            rolePermissionDTO.setPermissions(permissionCodeDTO);
        }

        return rolePermissionDTO;
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
