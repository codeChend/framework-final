package com.startdt.modules.role.dal.mapper;

import java.util.List;

import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfo;
import com.startdt.modules.role.dal.pojo.domain.ResourcePermissionInfoExample;
import org.apache.ibatis.annotations.Param;

public interface ResourcePermissionInfoMapper {
    long countByExample(ResourcePermissionInfoExample example);

    int deleteByExample(ResourcePermissionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insertBatch(List<ResourcePermissionInfo> records);

    int insert(ResourcePermissionInfo record);

    int insertSelective(ResourcePermissionInfo record);

    ResourcePermissionInfo selectOneByExample(ResourcePermissionInfoExample example);

    List<ResourcePermissionInfo> selectByExamplePaging(@Param("example") ResourcePermissionInfoExample example, @Param("offset") int offset, @Param("limit") int limit);

    List<ResourcePermissionInfo> selectByExample(ResourcePermissionInfoExample example);

    ResourcePermissionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResourcePermissionInfo record, @Param("example") ResourcePermissionInfoExample example);

    int updateByExample(@Param("record") ResourcePermissionInfo record, @Param("example") ResourcePermissionInfoExample example);

    int updateByPrimaryKeySelective(ResourcePermissionInfo record);

    int updateByPrimaryKey(ResourcePermissionInfo record);

    List<ResourcePermissionInfo> selectByCodes(List<String> codes);
}