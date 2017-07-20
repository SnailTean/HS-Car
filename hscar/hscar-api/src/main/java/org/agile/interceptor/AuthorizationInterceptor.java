package org.agile.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agile.annotation.IgnoreAuth;
import org.agile.common.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hundsun.hscar.entity.TokenEntity;
import com.hundsun.hscar.service.api.ITokenService;

/**
 * 权限(Token)验证
 * Spring提供了HandlerInterceptorAdapter这个适配器，继承此类，可以非常方便的实现自己的拦截器。
 * 他有三个方法：
 * (1)在preHandle中，可以进行编码、安全控制等处理;
 * (2)在postHandle中，有机会修改ModelAndView;
 * (3)在afterCompletion中，可以根据ex是否为null判断是否发生了异常，进行日志记录。
 * 当preHandle方法返回false时，从当前拦截器往回执行所有拦截器的afterCompletion方法，再退出拦截器链;
 * 当preHandle方法全为true时，执行下一个拦截器，直到所有拦截器执行完，再运行被拦截的Controller；
 * 然后进入拦截器链，运行所有拦截器的postHandle方法；
 * 完后从最后一个拦截器往回执行所有拦截器的afterCompletion方法；
 * 当有拦截器抛出异常时，会从当前拦截器往回执行所有拦截器的afterCompletion方法。
 * 
 * @author zhangmm
 * @email phoenix122411@126.com
 * @date 2017-05-06
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private ITokenService tokenService;

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

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());

        return true;
    }
}
