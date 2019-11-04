package com.startdt.modules.login.service.impl;

import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.login.pojo.UserLoginVO;
import com.startdt.modules.login.service.JwtTokenUtil;
import com.startdt.modules.login.service.StarterLoginService;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.service.ITbUserInfoService;
import com.startdt.modules.user.service.encode.PasswordEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author : weilong
 * @Description:
 * @Date: Create in 2019/8/28 下午5:05
 * @Modified By:
 */
@Configuration
public class StarterLoginServiceImpl implements StarterLoginService{

    @Autowired
    private ITbUserInfoService tbUserInfoService;

    @Autowired
    private PasswordEncode passwordEncode;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserLoginVO login(String userName, String password) {

        //密码需要客户端加密后传递
        TbUserInfo userInfo = tbUserInfoService.getByUserName(userName, null);
        if (userInfo == null) {
            throw new FrameworkException(BizResultConstant.NO_USER);
        }

        if (!passwordEncode.matches(password, userInfo.getPassword())) {
            throw new FrameworkException(BizResultConstant.PASSWORD_ERROR);
        }
        if (userInfo.getStatus() == 0) {
            throw new FrameworkException(BizResultConstant.USER_DISABLED);
        }
        //创建返回实体
        UserLoginVO userLoginVO = new UserLoginVO();
        String token = jwtTokenUtil.generateToken(userInfo.getUserName(), "login");
        userLoginVO.setUserInfo(userInfo);
        userLoginVO.setToken(token);
        return userLoginVO;
    }
}
