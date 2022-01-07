package com.juran.crowd.mvc.interceptor;

import com.juran.crowd.constant.CrowdConstant;
import com.juran.crowd.entity.Admin;
import com.juran.crowd.exception.AccessForbidException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 作者： Juran on 2022/1/7 12:28
 * 作者博客：iit.la
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //1.通过request对象获取Session对象
        HttpSession session = httpServletRequest.getSession();
        //2.通过session域中获取Admin对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);
        //3.判断admin对象是否为null
        if(admin == null){
            //4.抛出异常
            throw new AccessForbidException(CrowdConstant.MESSAGES_ACCESS_FORBID);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
