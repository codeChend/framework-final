package com.startdt.modules.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.page.PageResult;
import com.startdt.modules.common.utils.page.PageUtil;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.user.dal.mapper.TbUserInfoMapper;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfoExample;
import com.startdt.modules.user.dal.pojo.vo.UserDetailVO;
import com.startdt.modules.user.service.ITbUserInfoService;
import com.startdt.modules.user.service.encode.PasswordEncode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author  weilong
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
    public TbUserInfo getByUserName(String userName, Integer status) {
        TbUserInfoExample example = new TbUserInfoExample();
        TbUserInfoExample.Criteria or = example.or();
        or.andUserNameEqualTo(userName).andStatusEqualTo((byte)1);
        if(status != null){
            or.andStatusEqualTo(status.byteValue());
        }
        return userInfoMapper.selectOneByExample(example);
    }

    @Override
    public TbUserInfo insertUser(TbUserInfo entity) {
        TbUserInfo userInfo = getByUserName(entity.getUserName(),1);
        if(userInfo != null){
            throw new FrameworkException(BizResultConstant.USER_NAME_EXIST);
        }
        entity.setPassword(passwordEncode.encode(entity.getPassword()));
        int save = userInfoMapper.insertSelective(entity);
        log.info("插入用户信息返回:isSave:{},result:{}",save,JSON.toJSONString(entity));
        if(save<1){
            throw new FrameworkException(BizResultConstant.DB_MODIFY_ERROR);
        }
        return entity;
    }

    @Override
    public TbUserInfo modifyUser(TbUserInfo userInfo) {
        int update = 0;
        //查询该用户是否存在
        TbUserInfo userVO = userInfoMapper.selectByPrimaryKey(userInfo.getId());

        if(userVO == null || userVO.getStatus() == 0){
            throw new FrameworkException(BizResultConstant.NO_USER);
        }

        if (userInfo.getId()==null){
            throw new FrameworkException(BizResultConstant.ID_BLANK);
        }else {
            TbUserInfoExample example = new TbUserInfoExample();
            example.or().andIdEqualTo(userInfo.getId()).andStatusEqualTo((byte)1);
            userInfo.setPassword(passwordEncode.encode(userInfo.getPassword()));
            update = userInfoMapper.updateByExampleSelective(userInfo,example);
        }
        if(update<1){
            throw new FrameworkException(BizResultConstant.DB_MODIFY_ERROR);
        }
        return userInfo;
    }

    @Override
    public Integer disableUser(Integer userId) {
        TbUserInfo userInfo = new TbUserInfo();
        userInfo.setId(userId);
        userInfo.setStatus((byte)0);

        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public TbUserInfo getUserById(Integer userId) {
        TbUserInfo tbUserInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(tbUserInfo == null || tbUserInfo.getStatus() == 0){
            throw new FrameworkException(BizResultConstant.NO_USER);
        }
        return tbUserInfo;
    }

    @Override
    public TbUserInfo editPwd(Integer userId, String oldPwd, String newPwd) {
        TbUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo==null || userInfo.getStatus() == 0){
            throw new FrameworkException(BizResultConstant.NO_USER);
        }
        if(!passwordEncode.matches(oldPwd,userInfo.getPassword())){
            throw new FrameworkException(BizResultConstant.PASSWORD_ERROR);
        }
        TbUserInfo editPwd = new TbUserInfo();
        editPwd.setId(userId);
        editPwd.setPassword(passwordEncode.encode(newPwd));
        int update = userInfoMapper.updateByPrimaryKeySelective(editPwd);
        if(update<1){
            throw new FrameworkException(BizResultConstant.DB_MODIFY_ERROR);
        }

        return editPwd;
    }

    @Override
    public TbUserInfo editPwd(Integer userId, String oldPwd, String newPwd, String confirmNewPwd) {
        TbUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if(userInfo==null || userInfo.getStatus() == 0){
            throw new FrameworkException(BizResultConstant.NO_USER);
        }
        if(!newPwd.equals(confirmNewPwd)) {
            throw new FrameworkException(BizResultConstant.USER_NEW_PASSWORD_DISACCORD);
        }
        if(passwordEncode.matches(oldPwd, newPwd)) {
            throw new FrameworkException(BizResultConstant.USER_NEW_OLD_ACCORD);
        }
        if(!passwordEncode.matches(oldPwd,userInfo.getPassword())){
            throw new FrameworkException(BizResultConstant.PASSWORD_ERROR);
        }
        TbUserInfo editPwd = new TbUserInfo();
        editPwd.setId(userId);
        editPwd.setPassword(passwordEncode.encode(newPwd));

        int update = userInfoMapper.updateByPrimaryKeySelective(editPwd);
        if(update<1){
            throw new FrameworkException(BizResultConstant.DB_MODIFY_ERROR);
        }

        return editPwd;
    }

    @Override
    public List<TbUserInfo> listUsers(TbUserInfoExample example) {

        return userInfoMapper.selectByExample(example);
    }

    @Override
    public void checkUserName(String loginName) {

    }

    @Override
    public PageResult<UserDetailVO> selectByExamplePaging(int currentPage, int pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        TbUserInfoExample example = new TbUserInfoExample();
        example.or().andStatusEqualTo((byte)1);

        List<TbUserInfo> dataList = userInfoMapper.selectByExample(example);
        PageInfo<TbUserInfo> pageInfo = new PageInfo<>(dataList);

        return PageUtil.convertPage(pageInfo,UserDetailVO.class);
    }

    @Override
    @Transactional
    public List<UserDetailVO> batchInsertUser(List<TbUserInfo> userInfoList) {
        //过滤重复账号
        Map<String, TbUserInfo> userInfoMap = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        userInfoList.forEach(userInfo -> {
            String userName = userInfo.getUserName();
            TbUserInfo userMap = userInfoMap.get(userName);
            if (userMap != null) {
                sb.append("该").append(userName).append("账号重复").append(";");
            }
            TbUserInfoExample example = new TbUserInfoExample();
            example.or().andStatusEqualTo((byte) 1).andUserNameEqualTo(userName);
            List<TbUserInfo> selectUser = userInfoMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(selectUser)) {
                sb.append("该").append(userName).append("账号在数据库已存在");
            }
            //密码加密
            userInfo.setPassword(passwordEncode.encode(userInfo.getPassword()));
            userInfoMap.put(userName, userInfo);
        });

        if (!StringUtils.isEmpty(sb)) {
            throw new FrameworkException(40000, sb.toString());
        }

        //批量插入
        int insert = userInfoMapper.insertBatch(userInfoList);

        if (insert != userInfoList.size()) {
            //事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new FrameworkException(BizResultConstant.DB_MODIFY_ERROR);
        }
        return BeanConverter.mapList(userInfoList, UserDetailVO.class);
    }

}
