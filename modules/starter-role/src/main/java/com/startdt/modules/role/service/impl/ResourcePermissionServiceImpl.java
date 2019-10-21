package com.startdt.modules.role.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.enums.UrlMethodEnum;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.role.dal.mapper.ResourcePermissionInfoMapper;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfo;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfoExample;
import com.startdt.modules.role.dal.pojo.dto.UrlMethodDTO;
import com.startdt.modules.role.dal.pojo.request.permission.PermissionReq;
import com.startdt.modules.role.dal.pojo.dto.PermissionNodeDTO;
import com.startdt.modules.role.service.IResourcePermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
        example.setOrderByClause("code DESC");
        String code = "100";
        int sort = 0;
        ResourcePermissionInfo resourcePermissionInfo = resourcePermissionInfoMapper.selectOneByExample(example);
        if(resourcePermissionInfo != null){
            String mid = "";
            String parent = resourcePermissionInfo.getParentCode();
            if(!StringUtils.isEmpty(parent)){
                mid = resourcePermissionInfo.getCode().substring(parent.length()+1);
                parent += "_";
            }else{
                mid = resourcePermissionInfo.getCode();
                parent = "";
            }
            code = parent + (Long.valueOf(mid) + 1) + "";
            sort = resourcePermissionInfo.getSort() + 1;
        }
        permissionNodeDTOS.setCode(code);
        //递归拆分所有节点list
        recursionResource(permissionNodeDTOS,saveList,sort);

        Map<String,ResourcePermissionInfo> permissionMap = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        saveList.forEach(permissionInfo -> {
            //遍历是否有重复的权限值
            String value = permissionInfo.getValue();
            if(permissionMap.get(value)!=null){
                sb.append("该").append(value).append("权限值重复;");
            }
            ResourcePermissionInfoExample example1 = new ResourcePermissionInfoExample();
            example1.or().andValueEqualTo(value).andIsDeleteEqualTo((byte)0);

            ResourcePermissionInfo result = resourcePermissionInfoMapper.selectOneByExample(example1);
            if(result != null){
                sb.append("该").append(value).append("权限值已存在;");
            }
            permissionMap.put(value,permissionInfo);
        });

        if(StringUtils.isNotBlank(sb)){
            throw new FrameworkException(40000,sb.toString());
        }

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
            if(resourcePermissionInfo == null){
                throw new FrameworkException(BizResultConstant.PERMISSION_IS_NOT_EXIST);
            }
            String compareParentCode = StringUtils.isBlank(resourcePermissionInfo.getParentCode())?"":resourcePermissionInfo.getParentCode();
            if(sort == 0){
                parentCode = compareParentCode;
            }else if(!parentCode.equals(compareParentCode)){
                throw new FrameworkException(BizResultConstant.PERMISSION_IS_NOT_SAME_LEVEL);
            }
            resourcePermissionInfo.setSort(sort);
            sort++;
            list.add(resourcePermissionInfo);
        }

        list.forEach(resourcePermissionInfo -> {
            ResourcePermissionInfo entity = new ResourcePermissionInfo();
            entity.setSort(resourcePermissionInfo.getSort());
            entity.setGmtModified(new Date());
            entity.setId(resourcePermissionInfo.getId());
            resourcePermissionInfoMapper.updateByPrimaryKeySelective(entity);
        });

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
    public List<PermissionNodeDTO> permissionNodeSelective() {
        ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
        example.or().andIsDeleteEqualTo((byte)0).andParentCodeIsNull();
        example.setOrderByClause("sort ASC");

        List<ResourcePermissionInfo> list = resourcePermissionInfoMapper.selectByExample(example);

        List<PermissionNodeDTO> nodeDTOS = new ArrayList<>();

        list.forEach(resourcePermissionInfo -> {
            PermissionNodeDTO permissionNodeDTO = BeanConverter.convert(resourcePermissionInfo,PermissionNodeDTO.class);
            nodeDTOS.add(permissionNodeDTO);
            String resUrl = resourcePermissionInfo.getResUrl();
            if(StringUtils.isNotBlank(resUrl)){
                if(resUrl.contains(UrlMethodEnum.URL.getCode()) && resUrl.contains(UrlMethodEnum.METHOD.getCode())){
                    UrlMethodDTO urlMethodDTO = JSON.parseObject(resUrl,UrlMethodDTO.class);
                    permissionNodeDTO.setUrlMethod(urlMethodDTO);
                }
            }
        });

        recursionPermissionSon(nodeDTOS);

        return nodeDTOS;
    }

    @Override
    public List<ResourcePermissionInfo> permissionInfoByCodes(List<String> codes) {
        if(CollectionUtils.isEmpty(codes)){
            return Collections.emptyList();
        }
        return resourcePermissionInfoMapper.selectByCodes(codes);
    }

    @Override
    public List<ResourcePermissionInfo> permissionInfoByParentCode(String parentCode) {
        ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
        example.or().andParentCodeEqualTo(parentCode).andIsDeleteEqualTo((byte)0);

        example.setOrderByClause("sort ASC");

        return resourcePermissionInfoMapper.selectByExample(example);
    }

    @Override
    public ResourcePermissionInfo permissionInfoByCode(String code) {
        ResourcePermissionInfoExample example = new ResourcePermissionInfoExample();
        example.or().andCodeEqualTo(code).andIsDeleteEqualTo((byte)0);
        return resourcePermissionInfoMapper.selectOneByExample(example);
    }

    private void recursionResource(PermissionNodeDTO permissionNodeDTO,List<ResourcePermissionInfo> list,int sort){

        ResourcePermissionInfo resourcePermissionInfo = BeanConverter.convert(permissionNodeDTO,ResourcePermissionInfo.class);
        resourcePermissionInfo.setSort(sort);
        if(permissionNodeDTO.getUrlMethod() != null){
            resourcePermissionInfo.setResUrl(JSON.toJSONString(permissionNodeDTO.getUrlMethod()));
        }
        list.add(resourcePermissionInfo);

        if(!CollectionUtils.isEmpty(permissionNodeDTO.getPermissionNodeSon())){
            int sonSort = 0;
            for(PermissionNodeDTO permissionNodeSon : permissionNodeDTO.getPermissionNodeSon()){

                permissionNodeSon.setParentCode(permissionNodeDTO.getCode());
                permissionNodeSon.setCode(permissionNodeDTO.getCode() + "_" + (100 + sonSort));
                recursionResource(permissionNodeSon,list,sonSort);
                sonSort++;
            }
        }
    }

    private void recursionPermissionSon(List<PermissionNodeDTO> list){
        list.forEach(permissionNodeDTO -> {
            ResourcePermissionInfoExample resourcePermissionInfoExample = new ResourcePermissionInfoExample();
            resourcePermissionInfoExample.or().andParentCodeEqualTo(permissionNodeDTO.getCode()).andIsDeleteEqualTo((byte)0);
            resourcePermissionInfoExample.setOrderByClause("sort ASC");
            List<ResourcePermissionInfo> listSon = resourcePermissionInfoMapper.selectByExample(resourcePermissionInfoExample);
            List<PermissionNodeDTO> listNodeSon = Lists.newArrayListWithCapacity(listSon.size());
            if(!CollectionUtils.isEmpty(listSon)){
                listSon.forEach(resourcePermissionInfo -> {
                    PermissionNodeDTO permissionNode = BeanConverter.convert(resourcePermissionInfo,PermissionNodeDTO.class);
                    listNodeSon.add(permissionNode);
                    String resUrl = resourcePermissionInfo.getResUrl();
                    if(StringUtils.isNotBlank(resUrl)){
                        if(resUrl.contains(UrlMethodEnum.URL.getCode()) && resUrl.contains(UrlMethodEnum.METHOD.getCode())){
                            UrlMethodDTO urlMethodDTO = JSON.parseObject(resUrl,UrlMethodDTO.class);
                            permissionNode.setUrlMethod(urlMethodDTO);
                        }
                    }
                });
                permissionNodeDTO.setPermissionNodeSon(listNodeSon);
                recursionPermissionSon(listNodeSon);
            }
        });
    }
}
