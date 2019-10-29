package com.startdt.modules.role.dal.mapper;

import com.startdt.modules.role.dal.pojo.domain.GrantPermission;
import com.startdt.modules.role.dal.pojo.domain.GrantPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrantPermissionMapper {
    long countByExample(GrantPermissionExample example);

    int deleteByExample(GrantPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insertBatch(List<GrantPermission> records);

    int insert(GrantPermission record);

    int insertSelective(GrantPermission record);

    GrantPermission selectOneByExample(GrantPermissionExample example);

    List<GrantPermission> selectByExamplePaging(@Param("example") GrantPermissionExample example, @Param("offset") int offset, @Param("limit") int limit);

    List<GrantPermission> selectByExample(GrantPermissionExample example);

    GrantPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GrantPermission record, @Param("example") GrantPermissionExample example);

    int updateByExample(@Param("record") GrantPermission record, @Param("example") GrantPermissionExample example);

    int updateByPrimaryKeySelective(GrantPermission record);

    int updateByPrimaryKey(GrantPermission record);

    int updateBatchUserStatus(@Param("type") Integer type,@Param("principals") List<String> principals,@Param("spaceCode") String spaceCode);
}