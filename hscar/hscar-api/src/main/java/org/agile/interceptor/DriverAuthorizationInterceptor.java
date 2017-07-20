package org.agile.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agile.annotation.IgnoreAuth;
import org.agile.common.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hundsun.hscar.entity.DriverEntity;
import com.hundsun.hscar.entity.TokenEntity;
import com.hundsun.hscar.service.api.IDriverService;
import com.hundsun.hscar.service.api.ITokenService;

/**
 * 车主权限(Token)验证
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
public class DriverAuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private ITokenService tokenService;
    
    @Autowired
    private IDriverService driverService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IgnoreAuth annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        //token为空
        if(StringUtils.isBlank(token)) {
            throw new RRException("token不能为空", HttpStatus.UNAUTHORIZED.value());
        }

        //查询token信息
        TokenEntity tokenEntity = tokenService.queryObjectByToken(token);
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new RRException("token失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
        }
        
        //查询是否存在车主记录
        DriverEntity driverEntity = driverService.queryObjectByUserId(tokenEntity.getUserId());
        if(driverEntity == null) {
            throw new RRException("不存在车主记录，请注册");
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());

        return true;
    }
}
