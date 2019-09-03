package com.startdt.modules.user.dal.mapper;

import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserInfoMapper {
    long countByExample(TbUserInfoExample example);

    int deleteByExample(TbUserInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insertBatch(List<TbUserInfo> records);

    int insert(TbUserInfo record);

    int insertSelective(TbUserInfo record);

    TbUserInfo selectOneByExample(TbUserInfoExample example);

    List<TbUserInfo> selectByExamplePaging(@Param("example") TbUserInfoExample example, @Param("offset") int offset, @Param("limit") int limit);

    List<TbUserInfo> selectByExample(TbUserInfoExample example);

    TbUserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbUserInfo record, @Param("example") TbUserInfoExample example);

    int updateByExample(@Param("record") TbUserInfo record, @Param("example") TbUserInfoExample example);

    int updateByPrimaryKeySelective(TbUserInfo record);

    int updateByPrimaryKey(TbUserInfo record);
}