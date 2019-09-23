package com.startdt.modules.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.startdt.modules.common.pojo.Page;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.page.PageUtil;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.user.dal.mapper.TbUserInfoMapper;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfoExample;
import com.startdt.modules.user.dal.pojo.vo.UserDetailVO;
import com.startdt.modules.user.service.ITbUserInfoService;
import com.startdt.modules.user.service.encode.PasswordEncode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weilong
 * @since 2019-08-27
 */
@Configuration
@Slf4j
public class TbUserInfoServiceImpl implements ITbUserInfoService{

    @Autowired
    private PasswordEncode passwordEncode;

    @Autowired
    private TbUserInfoMapper userInfoMapper;

    @Override
    public Result<TbUserInfo> getByUserName(String userName, Integer status) {
        TbUserInfoExample example = new TbUserInfoExample();
        TbUserInfoExample.Criteria or = example.or();
        or.andUserNameEqualTo(userName);
        if(status != null){
            or.andStatusEqualTo(status.byteValue());
        }
        return Result.ofSuccess(userInfoMapper.selectOneByExample(example));
    }

    @Override
    public Result<TbUserInfo> insertUser(TbUserInfo entity) {
        Result<TbUserInfo> userInfo = getByUserName(entity.getUserName(),1);
        if(userInfo.isSuccess() && userInfo.getValue()!=null){
            return Result.ofErrorT(BizResultConstant.USER_NAME_EXIST);
        }
        entity.setPassword(passwordEncode.encode(entity.getPassword()));
        int save = userInfoMapper.insertSelective(entity);
        log.info("插入用户信息返回:isSave:{},result:{}",save,JSON.toJSONString(entity));
        if(save>0){
            return Result.ofSuccess(entity);
        }
        return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
    }

    @Override
    public Result<Integer> modifyUser(TbUserInfo userInfo) {
        int save = 0;
        if (userInfo.getId()==null){
            return Result.ofErrorT(BizResultConstant.ID_BLANK);
        }else {
            TbUserInfoExample example = new TbUserInfoExample();
            example.or().andIdEqualTo(userInfo.getId());
            save = userInfoMapper.updateByExampleSelective(userInfo,example);
        }
        return Result.ofSuccess(save);
    }

    @Override
    public Result<Integer> disableUser(Integer userId) {
        TbUserInfo userInfo = new TbUserInfo();
        userInfo.setId(userId);
        userInfo.setStatus((byte)0);
        int update = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return Result.ofSuccess(update);
    }

    @Override
    public Result<TbUserInfo> getUserById(Integer userId) {
        TbUserInfo tbUserInfo = userInfoMapper.selectByPrimaryKey(userId);
        return Result.ofSuccess(tbUserInfo);
    }

    @Override
    public Result<Integer> editPwd(Integer userId, String oldPwd, String newPwd) {
        TbUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(!passwordEncode.matches(oldPwd,userInfo.getPassword())){
            return Result.ofErrorT(BizResultConstant.PASSWORD_ERROR);
        }
        TbUserInfo editPwd = new TbUserInfo();
        editPwd.setId(userId);
        editPwd.setPassword(passwordEncode.encode(newPwd));
        int update = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return Result.ofSuccess(update);
    }

    @Override
    public Result<Integer> editPwd(Integer userId, String oldPwd, String newPwd, String confirmNewPwd) {
        TbUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
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
        int update = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return Result.ofSuccess(update);
    }

    @Override
    public Result<List<TbUserInfo>> listUsers(TbUserInfoExample example) {

        return Result.ofSuccess(userInfoMapper.selectByExample(example));
    }

    @Override
    public void checkUserName(String loginName) {

    }

    @Override
    public Page<UserDetailVO> selectByExamplePaging(TbUserInfoExample example, int currentPage, int pageSize) {

        PageHelper.startPage(currentPage, pageSize,true,true);

        if(example == null){
            example = new TbUserInfoExample();
        }
        List<TbUserInfo> dataList = userInfoMapper.selectByExample(example);
        PageInfo<TbUserInfo> pageInfo = new PageInfo<>(dataList);

        return PageUtil.convertPage(pageInfo,UserDetailVO.class);
    }

    @Override
    @Transactional
    public Result<List<UserDetailVO>> batchInsertUser(List<TbUserInfo> userInfoList) {
        //过滤重复账号
        Map<String,TbUserInfo> userInfoMap = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        userInfoList.forEach(userInfo -> {
            String userName = userInfo.getUserName();
            TbUserInfo userMap = userInfoMap.get(userName);
            if(userMap!=null){
                sb.append("该"+userName+"账号重复"+";");
            }
            TbUserInfoExample example = new TbUserInfoExample();
            example.or().andStatusEqualTo((byte)1).andUserNameEqualTo(userName);
            List<TbUserInfo> selectUser = userInfoMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(selectUser)){
                sb.append("该"+userName+"账号在数据库已存在"+"");
            }
            userInfoMap.put(userName,userInfo);
        });

        if(!StringUtils.isEmpty(sb)){
            throw new FrameworkException(3000,sb.toString());
        }

        //批量插入
        int insert = userInfoMapper.insertBatch(userInfoList);

        if(insert != userInfoList.size()){
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.ofErrorT(BizResultConstant.DB_MODIFY_ERROR);
        }
        return Result.ofSuccess(BeanConverter.mapList(userInfoList,UserDetailVO.class));
    }

}
