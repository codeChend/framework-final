package com.startdt.modules.role.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.role.dal.mapper.RolePermissionInfoMapper;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.dal.pojo.dto.RolePermissionDTO;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<RolePermissionInfo> insertRolePermission(RolePermissionDTO rolePermissionDTO) {
        RolePermissionInfo rolePermissionInfo = BeanConverter.convert(rolePermissionDTO,RolePermissionInfo.class);
        rolePermissionInfo.setPermission(JSON.toJSONString(rolePermissionDTO.getPermissions()));
        rolePermissionInfo.setId(null);
        boolean save = this.save(rolePermissionInfo);
        if(!save){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }
        return Result.ofSuccess(rolePermissionInfo);
    }

    @Override
    public Result<Integer> deleteRolePermission(Integer id) {
        RolePermissionInfo rolePermissionInfo = new RolePermissionInfo();
        rolePermissionInfo.setId(id);
        rolePermissionInfo.setStatus(0);
        boolean update = this.updateById(rolePermissionInfo);
        if(!update){
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }
        return Result.ofSuccess(1);
    }

    @Override
    public Result<RolePermissionInfo> editRole(RolePermissionInfo roleInfo) {
        if (roleInfo.getId() != null) {
            this.updateById(roleInfo);
        }else {
            return Result.ofErrorT(BizResultConstant.ID_BLANK);
        }
        return Result.ofSuccess(roleInfo);
    }

    @Override
    public Result<RolePermissionInfo> getRoleById(Integer id) {
        return Result.ofSuccess(this.getById(id));
    }

    @Override
    public Result<List<RolePermissionInfo>> listRole(QueryWrapper<RolePermissionInfo> queryWrapper) {
        return Result.ofSuccess(this.list(queryWrapper));
    }

    @Override
    public Result<RolePermissionInfo> getRoleByName(String roleName, Integer status) {
        QueryWrapper<RolePermissionInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",roleName);
        queryWrapper.eq("status",status);
        return Result.ofSuccess(this.getOne(queryWrapper));
    }


}
