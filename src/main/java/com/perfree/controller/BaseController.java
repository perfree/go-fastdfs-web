package com.perfree.controller;

import com.perfree.entity.Peers;
import com.perfree.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {

    /**
     * 获取已登录用户信息
     * @return User
     */
    public User getUser(){
        Subject subject = SecurityUtils.getSubject();
        User user=new User();
        BeanUtils.copyProperties(subject.getPrincipals().getPrimaryPrincipal(), user);
        return user;
    }

    /**
     * 获取当前用户使用的集群信息
     * @return Peers
     */
    public Peers getPeers(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Peers) request.getSession().getAttribute("peers");
    }
}
