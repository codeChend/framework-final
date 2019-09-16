package com.startdt.modules.role.dal.mapper;

import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfo;
import com.startdt.modules.role.dal.pojo.domain.RolePermissionInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weilong
 */
public interface RolePermissionInfoMapper {
    long countByExample(RolePermissionInfoExample example);

    int deleteByExample(RolePermissionInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insertBatch(List<RolePermissionInfo> records);

    int insert(RolePermissionInfo record);

    int insertSelective(RolePermissionInfo record);

    List<RolePermissionInfo> selectByExampleWithBLOBs(RolePermissionInfoExample example);

    RolePermissionInfo selectOneByExample(RolePermissionInfoExample example);

    List<RolePermissionInfo> selectByExamplePaging(@Param("example") RolePermissionInfoExample example, @Param("offset") int offset, @Param("limit") int limit);

    List<RolePermissionInfo> selectByExample(RolePermissionInfoExample example);

    RolePermissionInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermissionInfo record, @Param("example") RolePermissionInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") RolePermissionInfo record, @Param("example") RolePermissionInfoExample example);

    int updateByExample(@Param("record") RolePermissionInfo record, @Param("example") RolePermissionInfoExample example);

    int updateByPrimaryKeySelective(RolePermissionInfo record);

    int updateByPrimaryKeyWithBLOBs(RolePermissionInfo record);

    int updateByPrimaryKey(RolePermissionInfo record);

    List<RolePermissionInfo> selectByIds(List<String> roleIds);
}