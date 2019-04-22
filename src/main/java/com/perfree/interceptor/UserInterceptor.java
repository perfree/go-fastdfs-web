package com.perfree.interceptor;

import com.perfree.entity.User;
import com.perfree.mapper.PeersMapper;
import com.perfree.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户拦截
 */
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private PeersMapper peersMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Subject currentUser = SecurityUtils.getSubject();
        //判断当前用户是否为记住我登录且未存在集群信息session,将集群信息存入session
        if(!currentUser.isAuthenticated() && currentUser.isRemembered() && session.getAttribute("peers") == null){
            User shiroUser = (User) currentUser.getPrincipals().getPrimaryPrincipal();
            User user = userMapper.getUserById(shiroUser.getId());
            session.setAttribute("peers",peersMapper.getPeersById(user.getPeersId()));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
