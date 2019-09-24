package com.startdt.modules.role.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.enums.PrincipalTypeEnum;
import com.startdt.modules.common.utils.enums.ResourceTypeEnum;
import com.startdt.modules.common.utils.enums.RolePermissionEnum;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.page.PageUtil;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.role.dal.mapper.GrantPermissionMapper;
import com.startdt.modules.role.dal.pojo.domain.GrantPermission;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfo;
import com.startdt.modules.role.dal.pojo.dto.*;
import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import com.startdt.modules.role.service.IResourcePermissionService;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
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

    @Autowired
    private IResourcePermissionService resourcePermissionService;

    @Override
    public int grantUserRole(GrantUserRoleReq grantUserRoleReq) {
        //角色是否重复授予
        GrantPermissionExample example = new GrantPermissionExample();
        example.or().andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue()).andPrincipalPartEqualTo(grantUserRoleReq.getUserId().toString())
                .andResourcesTypeEqualTo(ResourceTypeEnum.ROLE.getCode().byteValue()).andResourcesEqualTo(grantUserRoleReq.getRoleCode().toString()).andStatusEqualTo((byte)1);
        List<GrantPermission> returnList = grantPermissionMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(returnList)){
            throw new FrameworkException(BizResultConstant.ROLE_IS_EXIST);
        }
        //对应user赋予角色信息
        GrantPermission entity = new GrantPermission();
        entity.setPrincipalPart(grantUserRoleReq.getUserId().toString());
        entity.setPrincipalPartType(PrincipalTypeEnum.USER.getCode().byteValue());
        entity.setResources(grantUserRoleReq.getRoleCode().toString());
        entity.setResourcesType(ResourceTypeEnum.ROLE.getCode().byteValue());
        entity.setNote(grantUserRoleReq.getNote());

        return grantPermissionMapper.insertSelective(entity);
    }

    @Override
    public int deleteUserRole(Integer userId, Integer roleId) {
        GrantPermission entity = new GrantPermission();
        entity.setStatus((byte)0);

        GrantPermissionExample example = new GrantPermissionExample();
        example.or().andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue()).andPrincipalPartEqualTo(userId.toString())
                .andResourcesEqualTo(roleId.toString()).andResourcesTypeEqualTo(ResourceTypeEnum.ROLE.getCode().byteValue());

        return grantPermissionMapper.updateByExampleSelective(entity,example);
    }

    @Override
    public PageResult<RoleInfoDTO> pageRoleByUserId(GrantPermissionExample example, int currentPage, int pageSize) {
        if(currentPage <= 0) {
            currentPage = 1;
        }
        if(pageSize <= 0) {
            pageSize = 10;
        }
        if(example == null){
            example = new GrantPermissionExample();
        }

        PageHelper.startPage(currentPage, pageSize);

        List<GrantPermission> dataList = grantPermissionMapper.selectByExample(example);

        PageInfo<GrantPermission> pageInfo = new PageInfo<>(dataList);

        return PageUtil.convertPage(pageInfo,RoleInfoDTO.class);
    }

    @Override
    public List<RoleInfoDTO> listByUserId(Integer userId) {
        if(userId == null){
            return Collections.emptyList();
        }
        GrantPermissionExample example = new GrantPermissionExample();
        example.or().andPrincipalPartEqualTo(String.valueOf(userId)).andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue());

        List<GrantPermission> grantPermissions = grantPermissionMapper.selectByExample(example);

        List<String> roleIds = grantPermissions.parallelStream().map(GrantPermission::getResources).collect(Collectors.toList());

        return BeanConverter.mapList(rolePermissionInfoService.listRole(roleIds), RoleInfoDTO.class);
    }

    @Override
    public List<ResourcePermissionInfo> permissionAllByUserId(Integer userId){
        //通过userId获取所有角色信息
        List<RoleInfoDTO> roleInfoDTOS = listByUserId(userId);
        //根据角色信息获取权限信息
        List<String> roleIds = roleInfoDTOS.parallelStream().map(roleInfoDTO -> String.valueOf(roleInfoDTO.getId())).collect(Collectors.toList());
        List<RolePermissionDTO> rolePermissionDTOS = rolePermissionInfoService.listRole(roleIds);

        //过滤出系统级权限
        List<PermissionCodeDTO> systemPermission = new ArrayList<>();
        rolePermissionDTOS.forEach(rolePermissionDTO -> {
            List<PermissionCodeDTO> permissionCodeDTOs = rolePermissionDTO.getPermissions()
                    .parallelStream().filter(permissionCode -> RolePermissionEnum.SYSTEM_PERMISSION.getCode() == permissionCode.getType())
                    .collect(Collectors.toList());

            systemPermission.addAll(permissionCodeDTOs);
        });
        //去重
        Set h = new HashSet(systemPermission);
        systemPermission.clear();
        systemPermission.addAll(h);
        //根据权限code获取权限信息
        List<String> permissionCodes = systemPermission.parallelStream().map(PermissionCodeDTO::getCode).collect(Collectors.toList());

        return resourcePermissionService.permissionInfoByCodes(permissionCodes);

    }

    @Override
    public List<PermissionNodeDTO> getMenuPermission(Integer userId) {
        List<ResourcePermissionInfo> permissionNodeDTOS = permissionAllByUserId(userId);

        //过滤父节点的权限集
        permissionNodeDTOS = permissionNodeDTOS
                .parallelStream().filter(permission -> StringUtils.isEmpty(permission.getParentCode()))
                .collect(Collectors.toList());

        List<PermissionNodeDTO> resultList = BeanConverter.mapList(permissionNodeDTOS,PermissionNodeDTO.class);

        List<String> permissionCodes = resultList.parallelStream().map(PermissionNodeDTO::getCode).collect(Collectors.toList());

        //递归所有有权限的点
        recursionNode(resultList,permissionCodes);

        return resultList;
    }

    @Override
    public List<PermissionAccessDTO> getRolePermission(Integer roleId) {
        if(roleId == null){
            return Collections.emptyList();
        }
        //获取角色所拥有的权限集
        RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(roleId);
        if(rolePermissionDTO == null || CollectionUtils.isEmpty(rolePermissionDTO.getPermissions())){
            throw new FrameworkException(BizResultConstant.ROLE_IS_NOT_EXIST_NO_AUTH);
        }

        List<PermissionCodeDTO> permissionCodeDTOS = rolePermissionDTO.getPermissions();

        permissionCodeDTOS = permissionCodeDTOS.parallelStream()
                .filter(permissionCodeDTO -> permissionCodeDTO.getType() == RolePermissionEnum.SYSTEM_PERMISSION.getCode()).collect(Collectors.toList());

        //根据权限code获取权限信息
        List<String> permissionCodes = permissionCodeDTOS.parallelStream().map(PermissionCodeDTO::getCode).collect(Collectors.toList());

        List<ResourcePermissionInfo> permissionInfos = resourcePermissionService.permissionInfoByCodes(permissionCodes);

        //过滤父节点的权限集
        List<ResourcePermissionInfo> permissionNodeDTOS = permissionInfos
                .parallelStream().filter(permission -> StringUtils.isEmpty(permission.getParentCode()))
                .collect(Collectors.toList());

        //获取权限树
        List<PermissionNodeDTO> authTree = resourcePermissionService.permissionNodeSelective(null);

        List<PermissionAccessDTO> permissionAccessDTOS = BeanConverter.mapList(authTree,PermissionAccessDTO.class);

        recursionAccessNode(permissionAccessDTOS,permissionCodes);

        return permissionAccessDTOS;
    }

    @Override
    public List<String> getUrlPermission(Integer userId) {
        //根据userId获取系统级所有权限集
        List<ResourcePermissionInfo> permissionInfoList = permissionAllByUserId(userId);

        return permissionInfoList.parallelStream().map(ResourcePermissionInfo::getResUrl).collect(Collectors.toList());
    }

    @Override
    public List<String> getBusinessPermission(Integer userId) {
        //通过userId获取所有角色信息
        List<RoleInfoDTO> roleInfoDTOS = listByUserId(userId);
        //根据角色信息获取权限信息
        List<String> roleIds = roleInfoDTOS.parallelStream().map(roleInfoDTO -> String.valueOf(roleInfoDTO.getId())).collect(Collectors.toList());
        List<RolePermissionDTO> rolePermissionDTOS = rolePermissionInfoService.listRole(roleIds);

        //过滤出业务级权限
        List<PermissionCodeDTO> businessPermission = new ArrayList<>();
        rolePermissionDTOS.forEach(rolePermissionDTO -> {
            List<PermissionCodeDTO> permissionCodeDTOs = rolePermissionDTO.getPermissions()
                    .parallelStream().filter(permissionCode -> RolePermissionEnum.BUSINESS_PERMISSION.getCode() == permissionCode.getType())
                    .collect(Collectors.toList());

            businessPermission.addAll(permissionCodeDTOs);
        });

        List<String> permissionCodes = businessPermission.parallelStream().map(PermissionCodeDTO::getCode).collect(Collectors.toList());

        //去重
        HashSet h = new HashSet(permissionCodes);
        permissionCodes.clear();
        permissionCodes.addAll(h);

        return permissionCodes;
    }

    @Override
    public int grantRolePermission(Integer roleId, List<String> permissionCode) {
        RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(roleId);
        if(rolePermissionDTO == null){
            throw new FrameworkException(BizResultConstant.ROLE_IS_NOT_EXIST);
        }
        List<PermissionCodeDTO> permissionCodeDTOS = rolePermissionDTO.getPermissions();

        List<ResourcePermissionInfo> list = resourcePermissionService.permissionInfoByCodes(permissionCode);

        Map<String,ResourcePermissionInfo> map = new HashMap<>();

        list.forEach(resourcePermissionInfo -> {
            map.put(resourcePermissionInfo.getCode(),resourcePermissionInfo);
        });

        permissionCode.forEach(permission -> {
            ResourcePermissionInfo resourcePermissionInfo = map.get(permission);
            if(resourcePermissionInfo != null){
                recursionGrantNode(permissionCodeDTOS,map,resourcePermissionInfo);
            }
        });

        return rolePermissionInfoService.modifyRolePermission(rolePermissionDTO);
    }

    @Override
    public int releaseRolePermission(Integer roleId, List<String> permissionCode) {
        RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(roleId);
        if(rolePermissionDTO == null){
            throw new FrameworkException(BizResultConstant.ROLE_IS_NOT_EXIST);
        }
        List<PermissionCodeDTO> permissionCodeDTOS = rolePermissionDTO.getPermissions();



        return 0;
    }

    private void recursionNode(List<PermissionNodeDTO> permissionNodeDTOS,List<String> permissionCodes){
        permissionNodeDTOS.forEach(permissionNodeDTO -> {
            List<ResourcePermissionInfo> resourcePermissionInfos1 = resourcePermissionService.permissionInfoByParentCode(permissionNodeDTO.getParentCode());
            List<PermissionNodeDTO> permissionSon = new ArrayList<>();
            if(!CollectionUtils.isEmpty(resourcePermissionInfos1)){
                resourcePermissionInfos1.forEach(resourcePermissionInfo -> {
                    if(permissionCodes.contains(resourcePermissionInfo.getCode())){
                        permissionSon.add(BeanConverter.convert(resourcePermissionInfo,PermissionNodeDTO.class));
                    }
                });
                recursionNode(permissionSon,permissionCodes);
            }
            permissionNodeDTO.setPermissionNodeSon(permissionSon);
        });
    }

    /**
     * 递归授权树
     * @param permissionAccessDTOS
     * @param permissionCodes
     */
    private void recursionAccessNode(List<PermissionAccessDTO> permissionAccessDTOS,List<String> permissionCodes){
        permissionAccessDTOS.forEach(permissionAccessDTO -> {
            String code = permissionAccessDTO.getCode();
            if(permissionCodes.contains(code)){
                permissionAccessDTO.setAccess(true);
            }
            if(CollectionUtils.isEmpty(permissionAccessDTO.getPermissionAccessSon())){
                //递归
                recursionAccessNode(permissionAccessDTO.getPermissionAccessSon(),permissionCodes);
            }
        });
    }

    /**
     * 递归给父节点授权
     * @param permissionCodeDTOS
     * @param map
     * @param resourcePermissionInfo
     */
    private void recursionGrantNode(List<PermissionCodeDTO> permissionCodeDTOS,Map<String,ResourcePermissionInfo> map,ResourcePermissionInfo resourcePermissionInfo){
        PermissionCodeDTO permissionCodeDTO = new PermissionCodeDTO();
        permissionCodeDTO.setCode(resourcePermissionInfo.getCode());
        permissionCodeDTO.setType(RolePermissionEnum.SYSTEM_PERMISSION.getCode());

        if(!permissionCodeDTOS.contains(permissionCodeDTO)){
            permissionCodeDTOS.add(permissionCodeDTO);
        }
        String parentCode = resourcePermissionInfo.getParentCode();
        if(!StringUtils.isEmpty(parentCode)){
            ResourcePermissionInfo parentResource = map.get(parentCode);
            if(parentResource == null){
                parentResource = resourcePermissionService.permissionInfoByCode(parentCode);
            }
            if(parentResource != null){
                map.remove(parentCode);

                recursionGrantNode(permissionCodeDTOS,map,parentResource);
            }

        }
    }
}
