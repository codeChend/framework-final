package com.startdt.modules.role.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.page.PageUtil;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.role.dal.mapper.RolePermissionInfoMapper;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfoExample;
import com.startdt.modules.role.dal.pojo.dto.PermissionCodeDTO;
import com.startdt.modules.role.dal.pojo.dto.RoleInfoDTO;
import com.startdt.modules.role.dal.pojo.dto.RolePermissionDTO;
import com.startdt.modules.role.dal.pojo.request.role.ModifyRoleInfoReq;
import com.startdt.modules.role.dal.pojo.request.role.SaveRoleInfoReq;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
@Slf4j
public class RolePermissionInfoServiceImpl implements IRolePermissionInfoService {

    @Autowired
    private RolePermissionInfoMapper rolePermissionInfoMapper;

    @Override
    public RoleInfoDTO insertRole(SaveRoleInfoReq rolePermissionDTO) {
        RolePermissionInfo rolePermissionInfo = BeanConverter.convert(rolePermissionDTO,RolePermissionInfo.class);

        int save = rolePermissionInfoMapper.insertSelective(rolePermissionInfo);

        if(save < 1){
            throw new FrameworkException(BizResultConstant.DB_MODIFY_ERROR);
        }
        return BeanConverter.convert(rolePermissionInfo, RoleInfoDTO.class);
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
    public PageResult<RoleInfoDTO> pageRole(String platformCode, int currentPage, int pageSize) {
        if(currentPage <= 0) {
            currentPage = 1;
        }
        if(pageSize <= 0) {
            pageSize = 10;
        }
        RolePermissionInfoExample example = new RolePermissionInfoExample();
        RolePermissionInfoExample.Criteria criteria = example.or();
        criteria.andStatusEqualTo((byte)1);
        if(StringUtils.isNotBlank(platformCode)){
            criteria.andPlatformCodeEqualTo(platformCode);
        }

        PageHelper.startPage(currentPage, pageSize);

        List<RolePermissionInfo> dataList = rolePermissionInfoMapper.selectByExample(example);

        PageInfo<RolePermissionInfo> pageInfo = new PageInfo<>(dataList);


        log.info("分页结果为{}",JSON.toJSONString(pageInfo));
        PageResult<RoleInfoDTO> pageResult = PageUtil.convertPage(pageInfo);

        List<RoleInfoDTO> data = new ArrayList<>();
        dataList.forEach(rolePermissionInfo -> {
            RoleInfoDTO roleInfoDTO = BeanConverter.convert(rolePermissionInfo,RoleInfoDTO.class);
            roleInfoDTO.setGmtCreate(rolePermissionInfo.getGmtCreate().getTime());
            data.add(roleInfoDTO);
        });
        pageResult.setData(data);

        return pageResult;
    }

    /**
     * 获取角色列表
     *
     * @param platformCode 平台code
     * @return
     */
    @Override
    public List<RoleInfoDTO> listRoles(String platformCode) {
        RolePermissionInfoExample example = new RolePermissionInfoExample();
        RolePermissionInfoExample.Criteria criteria = example.or();
        criteria.andStatusEqualTo((byte)1);
        if(StringUtils.isNotBlank(platformCode)){
            criteria.andPlatformCodeEqualTo(platformCode);
        }

        List<RolePermissionInfo> dataList = rolePermissionInfoMapper.selectByExample(example);

        List<RoleInfoDTO> data = new ArrayList<>();
        dataList.forEach(rolePermissionInfo -> {
            RoleInfoDTO roleInfoDTO = BeanConverter.convert(rolePermissionInfo,RoleInfoDTO.class);
            roleInfoDTO.setGmtCreate(rolePermissionInfo.getGmtCreate().getTime());
            data.add(roleInfoDTO);
        });

        return data;
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
        List<PermissionCodeDTO> permissionCodeDTO = new ArrayList<>();
        if(!StringUtils.isEmpty(rolePermissionInfo.getPermission())){
            permissionCodeDTO = JSONArray.parseArray(rolePermissionInfo.getPermission(),PermissionCodeDTO.class);
        }
        rolePermissionDTO.setPermissions(permissionCodeDTO);

        return rolePermissionDTO;
    }

    @Override
    public List<RolePermissionDTO> listRole(List<String> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)){
            return Collections.emptyList();
        }
        List<Integer> ids = roleIds.parallelStream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
        List<RolePermissionInfo> rolePermissionInfos = rolePermissionInfoMapper.selectByIds(ids);

        List<RolePermissionDTO> resultList = Lists.newArrayList();

        rolePermissionInfos.forEach(rolePermissionInfo -> {
            RolePermissionDTO rolePermissionDTO = BeanConverter.convert(rolePermissionInfo,RolePermissionDTO.class);
            if(!StringUtils.isEmpty(rolePermissionInfo.getPermission())){
                List<PermissionCodeDTO> permissionCodeDTO = JSON.parseArray(rolePermissionInfo.getPermission(),PermissionCodeDTO.class);
                rolePermissionDTO.setPermissions(permissionCodeDTO);
            }
            resultList.add(rolePermissionDTO);
        });

        return resultList;
    }

    /**
     * 获取角色列表
     *
     * @param platformCode 平台code
     * @return
     */
    @Override
    public List<RolePermissionDTO> listRolesByCode(String platformCode) {
        RolePermissionInfoExample example = new RolePermissionInfoExample();
        RolePermissionInfoExample.Criteria criteria = example.or();
        criteria.andStatusEqualTo((byte)1);
        if(StringUtils.isNotBlank(platformCode)){
            criteria.andPlatformCodeEqualTo(platformCode);
        }

        List<RolePermissionInfo> rolePermissionInfos = rolePermissionInfoMapper.selectByExample(example);

        List<RolePermissionDTO> data = new ArrayList<>();
        rolePermissionInfos.forEach(rolePermissionInfo -> {
            RolePermissionDTO rolePermissionDTO = BeanConverter.convert(rolePermissionInfo, RolePermissionDTO.class);
            List<PermissionCodeDTO> permissionCodeDTO = JSON.parseArray(rolePermissionInfo.getPermission(),PermissionCodeDTO.class);
            rolePermissionDTO.setGmtCreate(rolePermissionInfo.getGmtCreate());
            rolePermissionDTO.setPermissions(permissionCodeDTO);
            data.add(rolePermissionDTO);
        });

        return data;
    }

    @Override
    public int modifyRolePermission(RolePermissionDTO permissionDTO) {

        RolePermissionInfo rolePermissionInfo = BeanConverter.convert(permissionDTO,RolePermissionInfo.class);
        rolePermissionInfo.setPermission(JSON.toJSONString(permissionDTO.getPermissions()));
        rolePermissionInfo.setGmtModified(new Date());

        return rolePermissionInfoMapper.updateByPrimaryKeySelective(rolePermissionInfo);
    }

}
