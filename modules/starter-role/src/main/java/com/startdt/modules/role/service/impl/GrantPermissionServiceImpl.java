package com.startdt.modules.role.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.RegexUtil;
import com.startdt.modules.common.utils.enums.*;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.page.PageUtil;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.role.dal.mapper.GrantPermissionMapper;
import com.startdt.modules.role.dal.pojo.domain.GrantPermission;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfo;
import com.startdt.modules.role.dal.pojo.dto.*;
import com.startdt.modules.role.dal.pojo.request.grant.BatchGrantReq;
import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import com.startdt.modules.role.service.IResourcePermissionService;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author  weilong
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


        List<String> roleIds = dataList.parallelStream().map(GrantPermission::getResources).collect(Collectors.toList());

        List<RolePermissionDTO> rolePermissionDTOS = rolePermissionInfoService.listRole(roleIds);

        List<RoleInfoDTO> roleInfoDTOS = BeanConverter.mapList(rolePermissionDTOS, RoleInfoDTO.class);

        return PageUtil.convertPage(pageInfo,roleInfoDTOS);
    }

    @Override
    public List<RolePermissionDTO> listByUserId(Integer userId) {
        if(userId == null){
            return Collections.emptyList();
        }
        GrantPermissionExample example = new GrantPermissionExample();
        example.or().andPrincipalPartEqualTo(String.valueOf(userId)).andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue());

        List<GrantPermission> grantPermissions = grantPermissionMapper.selectByExample(example);

        List<String> roleIds = grantPermissions.parallelStream().map(GrantPermission::getResources).collect(Collectors.toList());

        return rolePermissionInfoService.listRole(roleIds);
    }

    @Override
    public List<ResourcePermissionInfo> permissionAllByUserId(Integer userId){
        //通过userId获取所有角色信息
        List<RolePermissionDTO> roleInfoDTOS = listByUserId(userId);

        //过滤出系统级权限
        List<PermissionCodeDTO> systemPermission = new ArrayList<>();
        roleInfoDTOS.forEach(rolePermissionDTO -> {
            if(!CollectionUtils.isEmpty(rolePermissionDTO.getPermissions())){
                List<PermissionCodeDTO> permissionCodeDTOs = rolePermissionDTO.getPermissions()
                        .parallelStream().filter(permissionCode -> RolePermissionEnum.SYSTEM_PERMISSION.getCode() == permissionCode.getType())
                        .collect(Collectors.toList());

                systemPermission.addAll(permissionCodeDTOs);
            }
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

        //过滤菜单级父节点的权限集
        List<ResourcePermissionInfo> parentPermission = permissionNodeDTOS
                .parallelStream().filter(permission -> StringUtils.isEmpty(permission.getParentCode()))
                .filter(permissionInfo -> permissionInfo.getType()== PermissionTypeEnum.MENU_PERMISSION.getCode().byteValue())
                .collect(Collectors.toList());

        List<PermissionNodeDTO> resultList = new ArrayList<>();
        parentPermission.forEach(resourcePermission -> {
            PermissionNodeDTO permissionNodeDTO = BeanConverter.convert(resourcePermission,PermissionNodeDTO.class);
            if(StringUtils.isNotBlank(resourcePermission.getResUrl())){
                permissionNodeDTO.setUrlMethod(JSON.parseObject(resourcePermission.getResUrl(),UrlMethodDTO.class));
            }

            resultList.add(permissionNodeDTO);
        });

        List<String> permissionCodes = permissionNodeDTOS.parallelStream().map(ResourcePermissionInfo::getCode).collect(Collectors.toList());

        //递归所有有权限的点
        recursionNode(resultList,permissionCodes);

        return resultList;
    }

    @Override
    public List<String> getFunctionPermission(Integer userId) {
        List<ResourcePermissionInfo> permissionNodeDTOS = permissionAllByUserId(userId);

        //过滤功能权限集合
        List<String> parentPermission = permissionNodeDTOS
                .parallelStream()
                .filter(permissionInfo -> permissionInfo.getType()== PermissionTypeEnum.BUTTON_PERMISSION.getCode().byteValue())
                .map(ResourcePermissionInfo::getValue)
                .collect(Collectors.toList());

        return parentPermission;
    }

    @Override
    public List<PermissionAccessDTO> getRolePermission(Integer roleId) {
        if(roleId == null){
            return Collections.emptyList();
        }
        //获取角色所拥有的权限集
        RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(roleId);
        if(rolePermissionDTO == null){
            throw new FrameworkException(BizResultConstant.ROLE_IS_NOT_EXIST);
        }

        List<PermissionCodeDTO> permissionCodeDTOS = rolePermissionDTO.getPermissions();

        permissionCodeDTOS = permissionCodeDTOS.parallelStream()
                .filter(permissionCodeDTO -> permissionCodeDTO.getType() == RolePermissionEnum.SYSTEM_PERMISSION.getCode()).collect(Collectors.toList());

        //根据权限code获取权限信息
        List<String> permissionCodes = permissionCodeDTOS.parallelStream().map(PermissionCodeDTO::getCode).collect(Collectors.toList());

        //获取权限树
        List<PermissionNodeDTO> authTree = resourcePermissionService.permissionNodeSelective();

        List<PermissionAccessDTO> permissionAccessDTOS = new ArrayList<>();

        recursionAccessNode(permissionAccessDTOS,permissionCodes,authTree);

        return permissionAccessDTOS;
    }

    @Override
    public List<UrlMethodDTO> getUrlPermission(Integer userId) {
        //根据userId获取系统级所有权限集
        List<ResourcePermissionInfo> permissionInfoList = permissionAllByUserId(userId);

        List<String> resUrls = permissionInfoList.parallelStream().filter(permissionInfo -> StringUtils.isNotBlank(permissionInfo.getResUrl()))
                .map(ResourcePermissionInfo::getResUrl).collect(Collectors.toList());

        List<UrlMethodDTO> urlMethodDTOS = new ArrayList<>();

        resUrls.forEach(resUrl -> {
            if(resUrl.contains(UrlMethodEnum.URL.getCode()) && resUrl.contains(UrlMethodEnum.METHOD.getCode())){
                UrlMethodDTO urlMethodDTO = JSON.parseObject(resUrl,UrlMethodDTO.class);
                urlMethodDTOS.add(urlMethodDTO);
            }
        });

        return urlMethodDTOS;
    }

    @Override
    public List<String> getBusinessPermission(Integer userId) {
        //通过userId获取所有角色信息
        List<RolePermissionDTO> roleInfoDTOS = listByUserId(userId);

        //过滤出业务级权限
        List<PermissionCodeDTO> businessPermission = new ArrayList<>();
        roleInfoDTOS.forEach(rolePermissionDTO -> {
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

        Map<String,PermissionCodeDTO> codeDTOMap = new HashMap<>();

        list.forEach(resourcePermissionInfo -> {
            map.put(resourcePermissionInfo.getCode(),resourcePermissionInfo);
        });

        permissionCodeDTOS.forEach(permissionCodeDTO -> codeDTOMap.put(permissionCodeDTO.getCode(),permissionCodeDTO));

        permissionCode.forEach(permission -> {
            ResourcePermissionInfo resourcePermissionInfo = map.get(permission);
            if(resourcePermissionInfo != null){
                recursionGrantNode(codeDTOMap,map,resourcePermissionInfo);
            }
        });

        return rolePermissionInfoService.modifyRolePermission(rolePermissionDTO);
    }

    @Override
    public int rolePermissionsCover(Integer roleId, List<String> permissionCode) {

        RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(roleId);
        if(rolePermissionDTO == null){
            throw new FrameworkException(BizResultConstant.ROLE_IS_NOT_EXIST);
        }

        List<ResourcePermissionInfo> list = resourcePermissionService.permissionInfoByCodes(permissionCode);

        Map<String,ResourcePermissionInfo> map = new HashMap<>();

        Map<String,PermissionCodeDTO> codeDTOMap = new HashMap<>();

        list.forEach(resourcePermissionInfo -> map.put(resourcePermissionInfo.getCode(),resourcePermissionInfo));

        permissionCode.forEach(permission -> {
            ResourcePermissionInfo resourcePermissionInfo = map.get(permission);
            if(resourcePermissionInfo != null){
                recursionGrantNode(codeDTOMap,map,resourcePermissionInfo);
            }
        });

        if(codeDTOMap.size() != 0){
            List<PermissionCodeDTO> permissionCodeDTOS = Lists.newArrayListWithExpectedSize(codeDTOMap.size());
            for(String key : codeDTOMap.keySet()){
                permissionCodeDTOS.add(codeDTOMap.get(key));
            }

            rolePermissionDTO.setPermissions(permissionCodeDTOS);
        }

        return rolePermissionInfoService.modifyRolePermission(rolePermissionDTO);
    }

    @Override
    public int releaseRolePermission(Integer roleId, List<String> permissionCode) {
        RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(roleId);
        if(rolePermissionDTO == null){
            throw new FrameworkException(BizResultConstant.ROLE_IS_NOT_EXIST);
        }
        List<PermissionCodeDTO> permissionCodeDTOS = rolePermissionDTO.getPermissions();

        List<ResourcePermissionInfo> list = resourcePermissionService.permissionInfoByCodes(permissionCode);

        Map<String,PermissionCodeDTO> permissionCodeMap = new HashMap<>();
        //释放父级权限会释放所有子集权限
        permissionCodeDTOS.forEach(permissionCodeDTO -> permissionCodeMap.put(permissionCodeDTO.getCode(),permissionCodeDTO));

        list.forEach(permissionInfo -> {
            //查询是否已经释放权限，若已经释放则进入下一循环
            if(permissionCodeMap.get(permissionInfo.getCode()) == null){
                return;
            }
            //释放角色当前节点的权限
            permissionCodeMap.remove(permissionInfo.getCode());

            //获取当前节点所有子节点
            List<ResourcePermissionInfo> sonList = resourcePermissionService.permissionInfoByParentCode(permissionInfo.getCode());

            //释放已授权的子节点权限
            sonList.forEach(permissionSon -> {
                if(permissionCodeMap.get(permissionInfo.getCode()) != null){
                    permissionCodeMap.remove(permissionInfo.getCode());
                }
            });
        });
        permissionCodeDTOS.clear();

        permissionCodeMap.keySet().forEach(key -> permissionCodeDTOS.add(permissionCodeMap.get(key)));

        return rolePermissionInfoService.modifyRolePermission(rolePermissionDTO);
    }

    @Override
    public boolean checkAuth(Integer userId, String url,String httpMethod) {
        //通过userId获取所有权限url路径
        boolean check = false;
        List<UrlMethodDTO> permissionUrls = getUrlPermission(userId);
        for(UrlMethodDTO urlMethodDTO : permissionUrls) {
            String perUrl = urlMethodDTO.getUrl();
            String perMethod = urlMethodDTO.getMethod();
            if(!perMethod.equalsIgnoreCase(httpMethod)){
                continue;
            }
            //匹配url路径
            String regexUrl = RegexUtil.wildToRegex(perUrl).replaceAll("\\{\\w+}","([^/]+)");
            if(Pattern.compile(regexUrl).matcher(url).matches()){
                check = true;
                break;
            }
        }

        return check;
    }

    @Override
    public int batchGrantUserRole(BatchGrantReq batchGrantReq) {
        //过滤id的重复
        Map<Integer,GrantPermission> map = new HashMap<>();

        batchGrantReq.getRoleIds().forEach(roleIdsReq -> {
            GrantPermission grantPermission = new GrantPermission();
            grantPermission.setNote(roleIdsReq.getNote());
            grantPermission.setPrincipalPart(batchGrantReq.getUserId().toString());
            grantPermission.setPrincipalPartType(PrincipalTypeEnum.USER.getCode().byteValue());
            grantPermission.setResources(String.valueOf(roleIdsReq.getRoleCode()));
            grantPermission.setResourcesType(ResourceTypeEnum.ROLE.getCode().byteValue());

            map.put(roleIdsReq.getRoleCode(),grantPermission);
        });

        //获取当钱用户已绑定哪些角色
        GrantPermissionExample example = new GrantPermissionExample();
        example.or().andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue())
                .andResourcesTypeEqualTo(ResourceTypeEnum.ROLE.getCode().byteValue())
                .andPrincipalPartEqualTo(batchGrantReq.getUserId().toString()).andStatusEqualTo((byte)1);

        grantPermissionMapper.deleteByExample(example);

        //批量赋予角色
        List<GrantPermission> entitys = Lists.newArrayListWithCapacity(map.size());

        for(Integer key : map.keySet()){
            entitys.add(map.get(key));
        }

        if(CollectionUtils.isEmpty(entitys)){
            return 0;
        }

        return grantPermissionMapper.insertBatch(entitys);
    }

    private void recursionNode(List<PermissionNodeDTO> permissionNodeDTOS,List<String> permissionCodes){
        permissionNodeDTOS.forEach(permissionNodeDTO -> {
            List<ResourcePermissionInfo> resourcePermissionInfos1 = resourcePermissionService.permissionInfoByParentCode(permissionNodeDTO.getCode());
            List<PermissionNodeDTO> permissionSon = new ArrayList<>();
            if(!CollectionUtils.isEmpty(resourcePermissionInfos1)){
                resourcePermissionInfos1.forEach(resourcePermissionInfo -> {
                    if(permissionCodes.contains(resourcePermissionInfo.getCode())){
                        PermissionNodeDTO permissionSonNode = BeanConverter.convert(resourcePermissionInfo,PermissionNodeDTO.class);
                        if(StringUtils.isNotBlank(resourcePermissionInfo.getResUrl())){
                            permissionSonNode.setUrlMethod(JSON.parseObject(resourcePermissionInfo.getResUrl(),UrlMethodDTO.class));
                        }
                        permissionSon.add(permissionSonNode);
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
    private void recursionAccessNode(List<PermissionAccessDTO> permissionAccessDTOS,List<String> permissionCodes,List<PermissionNodeDTO> authTree){
        authTree.forEach(permissionNodeDTO -> {
            String code = permissionNodeDTO.getCode();
            PermissionAccessDTO permissionAccessDTO = BeanConverter.convert(permissionNodeDTO,PermissionAccessDTO.class);

            if(permissionCodes.contains(code)){
                permissionAccessDTO.setAccess(true);
            }else{
                permissionAccessDTO.setAccess(false);
            }
            if(!CollectionUtils.isEmpty(permissionNodeDTO.getPermissionNodeSon())){
                //递归
                List<PermissionAccessDTO> permissionAccessSon = new ArrayList<>();
                recursionAccessNode(permissionAccessSon,permissionCodes,permissionNodeDTO.getPermissionNodeSon());
                permissionAccessDTO.setPermissionAccessSon(permissionAccessSon);
            }
            permissionAccessDTOS.add(permissionAccessDTO);
        });
    }

    /**
     * 递归给父节点授权
     * @param codeDTOMap
     * @param map
     * @param resourcePermissionInfo
     */
    private void recursionGrantNode(Map<String,PermissionCodeDTO> codeDTOMap,Map<String,ResourcePermissionInfo> map,ResourcePermissionInfo resourcePermissionInfo){
        String code = resourcePermissionInfo.getCode();
        PermissionCodeDTO permissionCodeDTO = new PermissionCodeDTO();
        permissionCodeDTO.setCode(code);
        permissionCodeDTO.setType(RolePermissionEnum.SYSTEM_PERMISSION.getCode());

        codeDTOMap.putIfAbsent(code,permissionCodeDTO);

        String parentCode = resourcePermissionInfo.getParentCode();
        if(!StringUtils.isEmpty(parentCode)){
            ResourcePermissionInfo parentResource = map.get(parentCode);
            if(parentResource == null){
                parentResource = resourcePermissionService.permissionInfoByCode(parentCode);
                map.putIfAbsent(parentCode,parentResource);
            }
            if(parentResource != null && codeDTOMap.get(parentCode) == null){
                recursionGrantNode(codeDTOMap,map,parentResource);
            }

        }
    }

}
