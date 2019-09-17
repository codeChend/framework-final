package com.startdt.modules.role.service.impl;

import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.role.dal.mapper.ResourcePermissionInfoMapper;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfo;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfoExample;
import com.startdt.modules.role.dal.pojo.request.permission.PermissionReq;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.dal.pojo.dto.QueryPermissionDTO;
import com.startdt.modules.role.service.IResourcePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/9/6 下午3:02
 * @Modified By:
 */
@Service
public class ResourcePermissionServiceImpl implements IResourcePermissionService{

    @Autowired
    private ResourcePermissionInfoMapper resourcePermissionInfoMapper;

    @Override
    public int saveResourcePermission(PermissionNodeDTO permissionNodeDTOS) {
        if(permissionNodeDTOS == null){
            throw new FrameworkException(BizResultConstant.PARAM_ERROR);
        }
        List<ResourcePermissionInfo> saveList = new ArrayList<>();
        //获取最大的父级 code
        ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
        ResourcePermissionInfoExample.Criteria criteria = example.or();
        if(!StringUtils.isEmpty(permissionNodeDTOS.getParentCode())){
            criteria.andParentCodeEqualTo(permissionNodeDTOS.getParentCode());
        }else{
            criteria.andParentCodeIsNull();
            permissionNodeDTOS.setParentCode(null);
        }
        example.setOrderByClause("'id' DESC");
        String code = "100";
        int sort = 0;
        ResourcePermissionInfo resourcePermissionInfo = resourcePermissionInfoMapper.selectOneByExample(example);
        if(resourcePermissionInfo != null){
            code = Long.valueOf(resourcePermissionInfo.getCode()) + 1 + "";
            sort = resourcePermissionInfo.getSort() + 1;
        }
        //递归拆分所有节点list
        recursionResource(permissionNodeDTOS,saveList,sort);

        //批量保存资源信息
        return resourcePermissionInfoMapper.insertBatch(saveList);
    }

    @Override
    public int modifyResourcePermission(PermissionReq permissionReq) {
        //查询该权限是否存
        ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
        example.or().andCodeEqualTo(permissionReq.getCode()).andIsDeleteEqualTo((byte)0);
        ResourcePermissionInfo resourcePermissionInfo = resourcePermissionInfoMapper.selectOneByExample(example);
        if(resourcePermissionInfo == null){
            throw new FrameworkException(BizResultConstant.NO_CONTENT_DATA);
        }
        resourcePermissionInfo.setName(permissionReq.getName());
        resourcePermissionInfo.setValue(permissionReq.getValue());
        resourcePermissionInfo.setIcon(permissionReq.getIcon());

        return resourcePermissionInfoMapper.updateByExampleSelective(resourcePermissionInfo,example);
    }

    @Override
    public int sortPermission(List<String> permissionCodes) {
        if(CollectionUtils.isEmpty(permissionCodes)){
            throw new FrameworkException(BizResultConstant.PARAM_ERROR);
        }
        //判断是否属于同级别的权限
        int sort = 0;
        String parentCode = "";
        List<ResourcePermissionInfo> list = new ArrayList<>();
        for(String code : permissionCodes){
            ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
            example.or().andCodeEqualTo(code).andIsDeleteEqualTo((byte)0);
            ResourcePermissionInfo resourcePermissionInfo = resourcePermissionInfoMapper.selectOneByExample(example);
            if(sort == 0){
                parentCode = resourcePermissionInfo.getParentCode();
            }else if(!parentCode.equals(resourcePermissionInfo.getParentCode())){
                throw new FrameworkException(BizResultConstant.PERMISSION_IS_NOT_SAME_LEVEL);
            }
            resourcePermissionInfo.setSort(sort);
            sort++;
            list.add(resourcePermissionInfo);
        }

        list.forEach(resourcePermissionInfo -> resourcePermissionInfoMapper.updateByPrimaryKey(resourcePermissionInfo));

        return sort+1;
    }

    @Override
    public int deletePermission(String permissionCode) {
        ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
        example.or().andCodeEqualTo(permissionCode).andIsDeleteEqualTo((byte)0);
        ResourcePermissionInfo resourcePermissionInfo = resourcePermissionInfoMapper.selectOneByExample(example);
        if(resourcePermissionInfo == null){
            throw new FrameworkException(BizResultConstant.NO_CONTENT_DATA);
        }
        resourcePermissionInfo.setIsDelete((byte)1);
        resourcePermissionInfo.setGmtModified(new Date());

        return resourcePermissionInfoMapper.updateByPrimaryKey(resourcePermissionInfo);
    }

    @Override
    public List<PermissionNodeDTO> permissionNodeSelective(QueryPermissionDTO queryPermissionDTO) {
        ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
        ResourcePermissionInfoExample.Criteria criteria = example.or();
        if(!StringUtils.isEmpty(queryPermissionDTO.getCode()) || !StringUtils.isEmpty(queryPermissionDTO.getName())){
            criteria.andCodeEqualTo(queryPermissionDTO.getCode()).andNameLike(queryPermissionDTO.getName());
        }else {
            criteria.andParentCodeIsNull();
        }
        if(queryPermissionDTO.getType() != null){
            criteria.andTypeEqualTo(queryPermissionDTO.getType());
        }
        List<ResourcePermissionInfo> list = resourcePermissionInfoMapper.selectByExample(example);
        List<PermissionNodeDTO> nodeDTOS = BeanConverter.mapList(list,PermissionNodeDTO.class);
        recursionPermissionSon(nodeDTOS);

        return nodeDTOS;
    }

    @Override
    public List<ResourcePermissionInfo> permissionInfoByCodes(List<String> codes) {
        if(CollectionUtils.isEmpty(codes)){
            return Collections.emptyList();
        }
        return resourcePermissionInfoMapper.selectByIds(codes);
    }

    @Override
    public List<ResourcePermissionInfo> permissionInfoByParentCode(String parentCode) {
        ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
        example.or().andParentCodeEqualTo(parentCode).andIsDeleteEqualTo((byte)0);

        return resourcePermissionInfoMapper.selectByExample(example);
    }

    private void recursionResource(PermissionNodeDTO permissionNodeDTO,List<ResourcePermissionInfo> list,int sort){

        ResourcePermissionInfo resourcePermissionInfo = BeanConverter.convert(permissionNodeDTO,ResourcePermissionInfo.class);
        resourcePermissionInfo.setSort(sort);
        list.add(resourcePermissionInfo);

        if(!CollectionUtils.isEmpty(permissionNodeDTO.getPermissionNodeSon())){
            int sonSort = 0;
            for(PermissionNodeDTO permissionNodeSon : permissionNodeDTO.getPermissionNodeSon()){

                permissionNodeSon.setParentCode(permissionNodeDTO.getCode());
                permissionNodeSon.setCode(permissionNodeDTO.getCode() + "_" + 100 + sonSort);
                recursionResource(permissionNodeSon,list,sort);
                sort++;
            }
        }
    }

    private void recursionPermissionSon(List<PermissionNodeDTO> list){
        list.forEach(permissionNodeDTO -> {
            ResourcePermissionInfoExample resourcePermissionInfoExample = new ResourcePermissionInfoExample();
            resourcePermissionInfoExample.or().andParentCodeEqualTo(permissionNodeDTO.getCode()).andIsDeleteEqualTo((byte)0);
            List<ResourcePermissionInfo> listSon = resourcePermissionInfoMapper.selectByExample(resourcePermissionInfoExample);
            List<PermissionNodeDTO> listNodeSon = BeanConverter.mapList(listSon,PermissionNodeDTO.class);
            if(!CollectionUtils.isEmpty(listNodeSon)){
                permissionNodeDTO.setPermissionNodeSon(listNodeSon);
                recursionPermissionSon(listNodeSon);
            }
        });
    }
}
