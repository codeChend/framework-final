package com.startdt.modules.login.intercept;

import com.startdt.modules.common.utils.exception.UserException;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.common.utils.result.Result;
import com.startdt.modules.login.pojo.JwtConfig;
import com.startdt.modules.login.pojo.LoginUnFilter;
import com.startdt.modules.login.service.JwtTokenUtil;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.service.ITbUserInfoService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/28 上午11:40
 * @Modified By:
 */
public class BackLoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackLoginInterceptor.class);

    private ITbUserInfoService userInfoService;

    private JwtConfig jwtConfig;

    private LoginUnFilter loginUnFilter;

    public BackLoginInterceptor(ITbUserInfoService userInfoService, JwtConfig jwtConfig, LoginUnFilter loginUnFilter){
        this.userInfoService = userInfoService;
        this.jwtConfig = jwtConfig;
        this.loginUnFilter = loginUnFilter;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        String requestURI = request.getRequestURI();

        String method = request.getMethod();

        if(HttpMethod.OPTIONS.matches(method)){
            return super.preHandle(request,response,handler);
        }
        //非HandlerMethod则直接返回
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        LoginStarter loginStarter = handlerMethod.getMethod().getAnnotation(LoginStarter.class);
        List<String> unFilter = loginUnFilter.unFilterList();
        boolean needLogin = false;
        for(String s : unFilter){
            if(requestURI.contains(s)){
                needLogin = true;
            }
        }
        if(!needLogin){
            //无需登录
            return super.preHandle(request,response,handler);
        }
        String authHeader = request.getHeader(jwtConfig.getTokenHeader());
        if(authHeader != null && authHeader.startsWith(jwtConfig.getTokenHead())){
            String authToken = authHeader.substring(jwtConfig.getTokenHead().length());
            Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken(authToken);
            if(claimsFromToken == null){
                throw new UserException(BizResultConstant.TOKEN_VERIFY);
            }
            //获得用户，检查用户存在和用户状态
            String subject = claimsFromToken.getSubject();
            if(StringUtils.isEmpty(subject)){
                throw new UserException(BizResultConstant.NO_USER);
            }

            Result<TbUserInfo> userResult = userInfoService.getByUserName(subject,null);

            if(!userResult.isSuccess() || userResult.getValue() == null ){
                throw new UserException(BizResultConstant.NO_USER);
            }
            TbUserInfo userInfo = userResult.getValue();
            if(userInfo.getStatus() == 0){
                throw new UserException(BizResultConstant.USER_DISABLED);
            }
            Date expiration = claimsFromToken.getExpiration();
            if(expiration.getTime() < System.currentTimeMillis()){
                throw new UserException(BizResultConstant.ERROR_USER_SESSION_EXPIRED);
            }
            //存储用户账号信息
            CurrentUser.set(userInfo);

            return super.preHandle(request,response,handler);
        }else{
            throw new UserException(BizResultConstant.ERROR_USER_SESSION_EXPIRED);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        //单次回话结束删除登录变量
        CurrentUser.remove();
    }
}
