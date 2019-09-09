package com.startdt.modules.role.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
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
    public Result<Integer> insertRolePermission(SaveRoleInfoReq rolePermissionDTO) {
        RolePermissionInfo rolePermissionInfo = BeanConverter.convert(rolePermissionDTO,RolePermissionInfo.class);

        return Result.ofSuccess(rolePermissionInfoMapper.insertSelective(rolePermissionInfo));
    }

    @Override
    public Result<Integer> deleteRolePermission(Integer id) {
        RolePermissionInfo rolePermissionInfo = new RolePermissionInfo();
        rolePermissionInfo.setId(id);
        rolePermissionInfo.setStatus((byte)0);

        return Result.ofSuccess(rolePermissionInfoMapper.updateByPrimaryKeySelective(rolePermissionInfo));
    }

    @Override
    public Result<Integer> editRole(ModifyRoleInfoReq roleInfo) {
        RolePermissionInfo rolePermissionInfo = BeanConverter.convert(roleInfo,RolePermissionInfo.class);
        Integer save = 0;
        if (roleInfo.getId() != null) {
            save = rolePermissionInfoMapper.updateByPrimaryKeySelective(rolePermissionInfo);
        }else {
            return Result.ofErrorT(BizResultConstant.ID_BLANK);
        }
        return Result.ofSuccess(save);
    }

    @Override
    public Result<List<RoleInfoDTO>> listRole(RolePermissionInfoExample rolePermissionInfoExample) {
        List<RolePermissionInfo> rolePermissionInfos = rolePermissionInfoMapper.selectByExample(rolePermissionInfoExample);
        List<RoleInfoDTO> resultList = BeanConverter.mapList(rolePermissionInfos,RoleInfoDTO.class);

        return Result.ofSuccess(resultList);
    }

    @Override
    public Result<RoleInfoDTO> getRoleByName(String roleName, Integer status) {
        RolePermissionInfoExample example = new RolePermissionInfoExample();
        example.or().andRoleNameEqualTo(roleName).andStatusEqualTo(status.byteValue());
        RolePermissionInfo rolePermissionInfo = rolePermissionInfoMapper.selectOneByExample(example);

        return Result.ofSuccess(BeanConverter.convert(rolePermissionInfo,RoleInfoDTO.class));
    }

    @Override
    public Result<RolePermissionDTO> getRoleById(Integer id) {
        RolePermissionInfo rolePermissionInfo = rolePermissionInfoMapper.selectByPrimaryKey(id);
        if(rolePermissionInfo == null){
            return Result.ofErrorT(BizResultConstant.NO_CONTENT_DATA);
        }
        RolePermissionDTO rolePermissionDTO = BeanConverter.convert(rolePermissionInfo,RolePermissionDTO.class);
        if(!StringUtils.isEmpty(rolePermissionInfo.getPermission())){
            List<PermissionCodeDTO> permissionCodeDTO = JSONArray.parseArray(rolePermissionInfo.getPermission(),PermissionCodeDTO.class);
            rolePermissionDTO.setPermissions(permissionCodeDTO);
        }

        return Result.ofSuccess(rolePermissionDTO);
    }

    @Override
    public Result<List<PermissionNodeDTO>> getMenuPermission(String userId) {
        return null;
    }

    @Override
    public Result<List<String>> getUrlPermission(String userId) {
        return null;
    }

    @Override
    public Result<List<String>> getBussinessPermission(String userId) {
        return null;
    }


}
