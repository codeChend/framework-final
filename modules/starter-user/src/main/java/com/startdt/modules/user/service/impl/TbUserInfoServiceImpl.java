package com.startdt.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.user.dal.mapper.TbUserInfoMapper;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.service.ITbUserInfoService;
import com.startdt.modules.user.service.encode.PasswordEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weilong
 * @since 2019-08-27
 */
@Configuration
public class TbUserInfoServiceImpl extends ServiceImpl<TbUserInfoMapper, TbUserInfo> implements ITbUserInfoService {

    @Autowired
    private PasswordEncode passwordEncode;

    @Override
    public Result<TbUserInfo> getByUserName(String userName, Integer status) {
        QueryWrapper<TbUserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        if(status != null){
            queryWrapper.eq("status", status);
        }
        return Result.ofSuccess(this.getOne(queryWrapper));
    }

    @Override
    public Result<TbUserInfo> insertUser(TbUserInfo entity) {
        entity.setPassword(passwordEncode.encode(entity.getPassword()));
        boolean save = this.save(entity);
        if(save){
            return Result.ofSuccess(entity);
        }
        return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
    }

    @Override
    public Result<TbUserInfo> modifyUser(TbUserInfo userInfo) {
        if (userInfo.getId()==null){
            return Result.ofErrorT(BizResultConstant.ID_BLANK);
        }else {
            this.updateById(userInfo);
        }
        return Result.ofSuccess(userInfo);
    }

    @Override
    public Result<Integer> disableUser(Integer userId) {
        TbUserInfo userInfo = new TbUserInfo();
        userInfo.setId(userId);
        userInfo.setStatus(0);
        boolean update = this.updateById(userInfo);
        return Result.ofSuccess(update?1:0);
    }

    @Override
    public Result<TbUserInfo> getUserById(Integer userId) {
        System.out.println("userId:"+userId);
        TbUserInfo tbUserInfo = this.getById(userId);
        System.out.println("tbUserInfo:"+tbUserInfo);
        return Result.ofSuccess(tbUserInfo);
    }

    @Override
    public Result<Integer> editPwd(Integer userId, String oldPwd, String newPwd) {
        TbUserInfo userInfo = getById(userId);
        if(!passwordEncode.matches(oldPwd,userInfo.getPassword())){
            return Result.ofErrorT(BizResultConstant.PASSWORD_ERROR);
        }
        TbUserInfo editPwd = new TbUserInfo();
        editPwd.setId(userId);
        editPwd.setPassword(passwordEncode.encode(newPwd));
        boolean update = this.updateById(userInfo);
        return Result.ofSuccess(update?1:0);
    }

    @Override
    public Result<Integer> editPwd(Integer userId, String oldPwd, String newPwd, String confirmNewPwd) {
        TbUserInfo userInfo = getById(userId);
        if(!newPwd.equals(confirmNewPwd)) {
            return Result.ofErrorT(BizResultConstant.USER_NEW_PASSWORD_DISACCORD);
        }
        if(passwordEncode.matches(oldPwd, newPwd)) {
            return Result.ofErrorT(BizResultConstant.USER_NEW_OLD_ACCORD);
        }
        if(!passwordEncode.matches(oldPwd,userInfo.getPassword())){
            return Result.ofErrorT(BizResultConstant.PASSWORD_ERROR);
        }
        TbUserInfo editPwd = new TbUserInfo();
        editPwd.setId(userId);
        editPwd.setPassword(passwordEncode.encode(newPwd));
        boolean update = this.updateById(userInfo);
        return Result.ofSuccess(update?1:0);
    }

    @Override
    public Result<List<TbUserInfo>> listUsers(QueryWrapper<TbUserInfo> queryWrapper) {

        return Result.ofSuccess(this.list(queryWrapper));
    }

    @Override
    public void checkUserName(String loginName) {

    }
}
