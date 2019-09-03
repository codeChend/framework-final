package com.startdt.modules.user.service;

import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfoExample;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weilong
 * @since 2019-08-27
 */
public interface ITbUserInfoService {

    /**
     * 通过用户账号获取用户信息
     * @param userName  用户账号
     * @param status    状态，是否启用
     * @return
     */
    Result<TbUserInfo> getByUserName(String userName,Integer status);

    /***
     * 添加用户
     * @param entity
     * @return
     */
    Result<TbUserInfo> insertUser(TbUserInfo entity);

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    Result<Integer> modifyUser(TbUserInfo userInfo);

    /**
     * 通过id禁用用户
     * @param userId
     * @return
     */
    Result<Integer> disableUser(Integer userId);

    /**
     * 通过id获取用户信息
     * @param userId
     * @return
     */
    Result<TbUserInfo> getUserById(Integer userId);

    /**
     * 验证旧密码，更新新密码
     * @param userId 用户ID
     * @param oldPwd 就密码
     * @param newPwd 新密码
     * @return userId
     */
    Result<Integer> editPwd(Integer userId, String oldPwd, String newPwd);

    /**
     * 验证旧密码，更新新密码
     * @param userId 用户ID
     * @param oldPwd 就密码
     * @param newPwd 新密码
     * @param confirmNewPwd 确认新密码
     * @return userId
     */
    Result<Integer> editPwd(Integer userId, String oldPwd, String newPwd, String confirmNewPwd);


    /**
     * 通过条件获取用户列表
     * @return
     */
    Result<List<TbUserInfo>> listUsers(TbUserInfoExample example);

    /**
     * 检查用户名是否存在
     * @param loginName 登录用户名
     */
    void checkUserName(String loginName);

    /**
     * 分页根据条件查找用户信息
     * @param example
     * @param currentPage
     * @param pageSize
     * @return
     */
    Page<TbUserInfo> selectByExamplePaging(TbUserInfoExample example, int currentPage, int pageSize);
}
