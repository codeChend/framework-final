package com.startdt.modules.login.intercept;

import com.startdt.modules.common.utils.BeanConverter;
import com.startdt.modules.common.utils.RegexUtil;
import com.startdt.modules.common.utils.exception.FrameworkException;
import com.startdt.modules.common.utils.result.BizResultConstant;
import com.startdt.modules.login.pojo.JwtConfig;
import com.startdt.modules.login.pojo.LoginUnFilter;
import com.startdt.modules.login.pojo.LoginUrlDTO;
import com.startdt.modules.login.pojo.UserInfoCache;
import com.startdt.modules.login.service.JwtTokenUtil;
import com.startdt.modules.user.dal.pojo.domain.TbUserInfo;
import com.startdt.modules.user.service.ITbUserInfoService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Current;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: weilong
 * @Description:
 * @Date: Create in 2019/8/28 上午11:40
 * @Modified By:
 */
@Configuration
public class BackLoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackLoginInterceptor.class);

    @Autowired
    private ITbUserInfoService userInfoService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private LoginUnFilter loginUnFilter;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${starter.dev.enable:false}")
    private boolean devEnable;

    @Value("${starter.dev.userId:1}")
    private Integer userId;

    @Value("${starter.dev.userName:weilong}")
    private String userName;

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
        List<LoginUrlDTO> unFilter = loginUnFilter.unFilterList();
        boolean needLogin = false;

        String loginUrl = request.getRequestURI();

        for(LoginUrlDTO loginUrlDTO : unFilter){
            String filterMethod = loginUrlDTO.getMethod();
            String filterUrl = loginUrlDTO.getUrl();

            boolean methodFix = false;

            if(StringUtils.isBlank(filterMethod)){
                methodFix = true;
            }else{
                String[] filterMethodArray = filterMethod.split(":");
                for (String forMethod : filterMethodArray) {
                    if (forMethod.equalsIgnoreCase(method)) {
                        methodFix = true;
                        break;
                    }
                }
            }
            if(methodFix){
                String regexUrl = RegexUtil.wildToRegex(filterUrl).replaceAll("\\{\\w+}","([^/]+)");
                if(Pattern.compile(regexUrl).matcher(loginUrl).matches()){
                    needLogin = true;
                }
            }
        }

        if(!needLogin){
            //无需登录
            return super.preHandle(request,response,handler);
        }
        if(devEnable){
            UserInfoCache tbUserInfo = new UserInfoCache();
            tbUserInfo.setId(userId);
            tbUserInfo.setUserName(userName);

            CurrentUser.set(tbUserInfo);
            return super.preHandle(request,response,handler);
        }
        String authHeader = request.getHeader(jwtConfig.getTokenHeader());
        if(StringUtils.isBlank(authHeader)){
            authHeader = request.getParameter(jwtConfig.getTokenHeader());
        }
        if(authHeader != null && authHeader.startsWith(jwtConfig.getTokenHead())){
            String authToken = authHeader.substring(jwtConfig.getTokenHead().length());
            Claims claimsFromToken = jwtTokenUtil.getClaimsFromToken(authToken);
            if(claimsFromToken == null){
                throw new FrameworkException(BizResultConstant.TOKEN_VERIFY);
            }
            //获得用户，检查用户存在和用户状态
            String subject = claimsFromToken.getSubject();
            if(StringUtils.isEmpty(subject)){
                throw new FrameworkException(BizResultConstant.NO_USER);
            }

            TbUserInfo userInfo = userInfoService.getByUserName(subject,null);

            if(userInfo == null ){
                throw new FrameworkException(BizResultConstant.NO_USER);
            }
            if(userInfo.getStatus() == 0){
                throw new FrameworkException(BizResultConstant.USER_DISABLED);
            }
            Date expiration = claimsFromToken.getExpiration();
            if(expiration.getTime() < System.currentTimeMillis()){
                throw new FrameworkException(BizResultConstant.ERROR_USER_SESSION_EXPIRED);
            }
            UserInfoCache userInfoCache = BeanConverter.convert(userInfo,UserInfoCache.class);

            userInfoCache.setToken(authToken);
            //存储用户账号信息
            CurrentUser.set(userInfoCache);

            return super.preHandle(request,response,handler);
        }else{
            throw new FrameworkException(BizResultConstant.ERROR_USER_SESSION_EXPIRED);
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
