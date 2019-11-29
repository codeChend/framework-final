package com.startdt.modules.role.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import com.startdt.modules.role.dal.pojo.request.grant.GrantUserRoleReq;
import com.startdt.modules.role.service.IGrantPermissionService;
import com.startdt.modules.role.service.IResourcePermissionService;
import com.startdt.modules.role.service.IRolePermissionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weilong
 * @since 2019-08-28
 */
@Slf4j
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
        GrantPermissionExample.Criteria criteria = example.or();
        criteria.andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue()).andPrincipalPartEqualTo(grantUserRoleReq.getUserId().toString())
                .andResourcesTypeEqualTo(ResourceTypeEnum.ROLE.getCode().byteValue()).andResourcesEqualTo(grantUserRoleReq.getRoleCode().toString()).andStatusEqualTo((byte)1);
        if(StringUtils.isNotBlank(grantUserRoleReq.getSpaceCode())){
            criteria.andSpaceCodeEqualTo(grantUserRoleReq.getSpaceCode());
        }
        List<GrantPermission> returnList = grantPermissionMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(returnList)){
            return 1;
        }
        //对应user赋予角色信息
        GrantPermission entity = new GrantPermission();
        entity.setPrincipalPart(grantUserRoleReq.getUserId());
        entity.setPrincipalPartType(PrincipalTypeEnum.USER.getCode().byteValue());
        entity.setResources(grantUserRoleReq.getRoleCode().toString());
        entity.setResourcesType(ResourceTypeEnum.ROLE.getCode().byteValue());
        entity.setNote(grantUserRoleReq.getNote());
        if(StringUtils.isNotBlank(grantUserRoleReq.getSpaceCode())){
            entity.setSpaceCode(grantUserRoleReq.getSpaceCode());
        }

        return grantPermissionMapper.insertSelective(entity);
    }

    @Override
    public int grantUserRoleBatch(String userId, List<String> roleIds, String spaceCode) {
        //解绑原有的所有用户角色
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(spaceCode) || CollectionUtils.isEmpty(roleIds)){
            throw new FrameworkException(BizResultConstant.PARAM_ERROR);
        }
        GrantPermissionExample example = new GrantPermissionExample();
        GrantPermissionExample.Criteria criteria = example.or();
        criteria.andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue())
                .andPrincipalPartEqualTo(userId).andSpaceCodeEqualTo(spaceCode);

        int delete = grantPermissionMapper.deleteByExample(example);
        //批量绑定角色
        List<GrantPermission> entityS = new ArrayList<>();
        roleIds.forEach(roleId -> {
            GrantPermission entity = new GrantPermission();
            entity.setSpaceCode(spaceCode);
            entity.setResourcesType(ResourceTypeEnum.ROLE.getCode().byteValue());
            entity.setResources(roleId);
            entity.setPrincipalPartType(PrincipalTypeEnum.USER.getCode().byteValue());
            entity.setPrincipalPart(userId);
            entity.setStatus((byte)1);
            entity.setGmtCreate(new Date());
            entity.setGmtModified(new Date());
            entity.setNote("授予角色");

            entityS.add(entity);
        });

        return grantPermissionMapper.insertBatch(entityS);
    }

    @Override
    public int deleteUserRole(String userId, Integer roleId) {
        return deleteUserRole(userId,roleId,null);
    }

    @Override
    public int deleteUserRole(String userId, Integer roleId, String spaceCode) {
        GrantPermission entity = new GrantPermission();
        entity.setStatus((byte)0);

        GrantPermissionExample example = new GrantPermissionExample();
        GrantPermissionExample.Criteria criteria = example.or();
        criteria.andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue()).andPrincipalPartEqualTo(userId)
                .andResourcesEqualTo(roleId.toString()).andResourcesTypeEqualTo(ResourceTypeEnum.ROLE.getCode().byteValue());
        if(StringUtils.isNotBlank(spaceCode)){
            criteria.andSpaceCodeEqualTo(spaceCode);
        }

        return grantPermissionMapper.updateByExampleSelective(entity,example);
    }

    @Override
    public int deleteUserRoleBatch(List<String> userId, String spaceCode) {

        if(CollectionUtils.isEmpty(userId)){
            return 0;
        }

        if(StringUtils.isBlank(spaceCode)){
            spaceCode = null;
        }

        return grantPermissionMapper.updateBatchUserStatus(1,userId,spaceCode);
    }

    @Override
    public PageResult<RoleInfoDTO> pageRoleByUserId(String userId,String spaceCode, int currentPage, int pageSize) {
        if(currentPage <= 0) {
            currentPage = 1;
        }
        if(pageSize <= 0) {
            pageSize = 10;
        }

        GrantPermissionExample example = new GrantPermissionExample();
        GrantPermissionExample.Criteria criteria = example.or();
        criteria.andPrincipalPartEqualTo(userId).andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue())
                .andResourcesTypeEqualTo(ResourceTypeEnum.ROLE.getCode().byteValue()).andStatusEqualTo((byte)1);

        if(StringUtils.isNotBlank(spaceCode)){
            criteria.andSpaceCodeEqualTo(spaceCode);
        }

        PageHelper.startPage(currentPage, pageSize);

        List<GrantPermission> dataList = grantPermissionMapper.selectByExample(example);

        PageInfo<GrantPermission> pageInfo = new PageInfo<>(dataList);

        List<RoleInfoDTO> returnList = new ArrayList<>();

        pageInfo.getList().forEach(grantPermission -> {
            RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(Integer.valueOf(grantPermission.getResources()));
            RoleInfoDTO roleInfoDTO = BeanConverter.convert(rolePermissionDTO,RoleInfoDTO.class);

            returnList.add(roleInfoDTO);
        });

        return PageUtil.convertPage(pageInfo,returnList);
    }

    @Override
    public List<RolePermissionDTO> listByUserId(String userId) {
        return listByUserId(userId,"");
    }

    @Override
    public List<RolePermissionDTO> listByUserId(String userId, String spaceCode) {
        List<String> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(spaceCode)){
            list.add(spaceCode);
        }
        return listByUserId(userId,list);
    }

    @Override
    public List<RolePermissionDTO> listByUserId(String userId, List<String> spaceCode) {
        if(userId == null){
            return Collections.emptyList();
        }
        GrantPermissionExample example = new GrantPermissionExample();
        GrantPermissionExample.Criteria criteria = example.or();
        criteria.andPrincipalPartEqualTo(String.valueOf(userId)).andPrincipalPartTypeEqualTo(PrincipalTypeEnum.USER.getCode().byteValue());

        if(!CollectionUtils.isEmpty(spaceCode)){
            criteria.andSpaceCodeIn(spaceCode);
        }

        List<GrantPermission> grantPermissions = grantPermissionMapper.selectByExample(example);

        List<String> roleIds = grantPermissions.stream().map(GrantPermission::getResources).collect(Collectors.toList());

        return rolePermissionInfoService.listRole(roleIds);
    }

    @Override
    public List<ResourcePermissionInfo> permissionAllByUserId(String userId,String spaceCode){
        //通过userId获取所有角色信息
        List<RolePermissionDTO> roleInfoDTOS = listByUserId(userId,spaceCode);

        return getPermissionByRoleIds(roleInfoDTOS);
    }

    @Override
    public List<PermissionNodeDTO> getMenuPermission(String userId,List<String> spaceCode) {

        List<RolePermissionDTO> roleInfoDTOS = listByUserId(userId,spaceCode);

        log.info("listByUserId roleInfoDTOS:{}", JSONArray.toJSONString(roleInfoDTOS));

        List<ResourcePermissionInfo> permissionNodeDTOS = getPermissionByRoleIds(roleInfoDTOS);

        log.info("getPermissionByRoleIds permissionNodeDTOS:{}", JSONArray.toJSONString(permissionNodeDTOS));

        //过滤菜单级父节点的权限集
        List<ResourcePermissionInfo> parentPermission = permissionNodeDTOS
                .stream().filter(permission -> StringUtils.isEmpty(permission.getParentCode()))
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

        List<String> permissionCodes = permissionNodeDTOS.stream().map(ResourcePermissionInfo::getCode).collect(Collectors.toList());

        //递归所有有权限的点
        recursionNode(resultList,permissionCodes);

        return resultList;
    }

    @Override
    public List<RolePermissionNodeDTO> getMenuPermissionWithRole(String userId,List<String> spaceCode) {

        List<RolePermissionDTO> roleInfoDTOS = listByUserId(userId,spaceCode);

        log.info("listByUserId roleInfoDTOS:{}", JSONArray.toJSONString(roleInfoDTOS));

        List<ResourcePermissionInfo> permissionNodeDTOS = getPermissionByRoleIds(roleInfoDTOS);

        log.info("getPermissionByRoleIds permissionNodeDTOS:{}", JSONArray.toJSONString(permissionNodeDTOS));

        //过滤菜单级父节点的权限集
        List<ResourcePermissionInfo> parentPermission = permissionNodeDTOS
                .stream().filter(permission -> StringUtils.isEmpty(permission.getParentCode()))
                .filter(permissionInfo -> permissionInfo.getType()== PermissionTypeEnum.MENU_PERMISSION.getCode().byteValue())
                .collect(Collectors.toList());


        List<PermissionNodeDTO> resultList = new ArrayList<>();
        parentPermission.forEach(resourcePermission -> {
            PermissionNodeDTO permissionNodeDTO = BeanConverter.convert(resourcePermission,RolePermissionNodeDTO.class);
            if(StringUtils.isNotBlank(resourcePermission.getResUrl())){
                permissionNodeDTO.setUrlMethod(JSON.parseObject(resourcePermission.getResUrl(),UrlMethodDTO.class));
            }

            resultList.add(permissionNodeDTO);
        });

        List<String> permissionCodes = permissionNodeDTOS.stream().map(ResourcePermissionInfo::getCode).collect(Collectors.toList());

        //递归所有有权限的点
        recursionNode(resultList,permissionCodes);

        List<RolePermissionNodeDTO> rolePermissionNodeDTOList = new ArrayList<>();

        rolePermissionNodeDTOList = BeanConverter.mapList(resultList, RolePermissionNodeDTO.class);

        for (int i = 0; i < rolePermissionNodeDTOList.size(); i++) {
            RolePermissionNodeDTO rolePermissionNodeDTO = rolePermissionNodeDTOList.get(i);
            for (int j = 0; j < roleInfoDTOS.size(); j++) {
                List<PermissionCodeDTO> permissionCodeDTOS = roleInfoDTOS.get(j).getPermissions();
                for (int k = 0; k < permissionCodeDTOS.size(); k++) {
                    if (rolePermissionNodeDTO.getCode().equals(permissionCodeDTOS.get(k).getCode())) {
                        rolePermissionNodeDTOList.get(i).setRoleId(roleInfoDTOS.get(j).getId());
                        break;
                    }
                }
            }
        }

        return rolePermissionNodeDTOList;
    }

    @Override
    public List<String> getFunctionPermission(String userId,String spaceCode) {
        List<ResourcePermissionInfo> permissionNodeDTOS = permissionAllByUserId(userId,spaceCode);

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
    public List<UrlMethodDTO> getUrlPermission(String userId,String spaceCode) {
        //根据userId获取系统级所有权限集
        List<ResourcePermissionInfo> permissionInfoList = permissionAllByUserId(userId,spaceCode);

        List<String> resUrls = permissionInfoList.parallelStream().filter(permissionInfo -> StringUtils.isNotBlank(permissionInfo.getResUrl()))
                .map(ResourcePermissionInfo::getResUrl)
                .collect(Collectors.toList());

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
    public List<String> getBusinessPermission(String userId,String spaceCode) {
        //通过userId获取所有角色信息
        List<RolePermissionDTO> roleInfoDTOS = listByUserId(userId,spaceCode);

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
    public boolean checkAuth(String userId, String url,String httpMethod) {
        //通过userId获取所有权限url路径
        boolean check = false;
        List<UrlMethodDTO> permissionUrls = getUrlPermission(userId,null);
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
    public List<PermissionNodeDTO> getPermissionByRole(Integer roleId) {
        RolePermissionDTO rolePermissionDTO = rolePermissionInfoService.getRoleById(roleId);

        List<RolePermissionDTO> list = new ArrayList<>();
        list.add(rolePermissionDTO);
        List<ResourcePermissionInfo> permissionNodeDTOS = getPermissionByRoleIds(list);

        //过滤父节点的权限集
        List<ResourcePermissionInfo> parentPermission = permissionNodeDTOS
                .parallelStream().filter(permission -> StringUtils.isEmpty(permission.getParentCode()))
//                .filter(permissionInfo -> permissionInfo.getType()== PermissionTypeEnum.MENU_PERMISSION.getCode().byteValue())
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

    private List<ResourcePermissionInfo> getPermissionByRoleIds(List<RolePermissionDTO> roleInfoDTOS){

        //过滤出系统级权限
        List<PermissionCodeDTO> systemPermission = new ArrayList<>();
        roleInfoDTOS.forEach(rolePermissionDTO -> {
            if(!CollectionUtils.isEmpty(rolePermissionDTO.getPermissions())){
                List<PermissionCodeDTO> permissionCodeDTOs = rolePermissionDTO.getPermissions()
                        .stream().filter(permissionCode -> RolePermissionEnum.SYSTEM_PERMISSION.getCode() == permissionCode.getType())
                        .collect(Collectors.toList());

                systemPermission.addAll(permissionCodeDTOs);
            }
        });
        //去重
        Set h = new HashSet(systemPermission);
        systemPermission.clear();
        systemPermission.addAll(h);
        //根据权限code获取权限信息
        List<String> permissionCodes = systemPermission.stream().map(PermissionCodeDTO::getCode).collect(Collectors.toList());
        log.info("permissionCodes:[]", JSONArray.toJSONString(permissionCodes));
        return resourcePermissionService.permissionInfoByCodes(permissionCodes);
    }


}
