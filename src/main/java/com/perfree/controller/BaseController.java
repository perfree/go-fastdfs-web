package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import com.perfree.common.StringUtil;
import com.perfree.entity.Peers;
import com.perfree.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类
 * @Author Perfree
 * @Date 8:41 2019/6/6
 **/
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

    /**
     * 获取组名
     * @return String
     */
    public String getPeersGroupName(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Peers peers = (Peers) request.getSession().getAttribute("peers");
        return peers.getGroupName();
    }

    /**
     * 获取url前缀,(地址+组名)
     * @return String
     */
    public String getPeersUrl(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Peers peers = (Peers) request.getSession().getAttribute("peers");
        if(!StringUtil.isBlank(peers.getGroupName())){
            return  peers.getServerAddress()+"/"+peers.getGroupName();
        }
        return  peers.getServerAddress();
    }

    /**
     * 获取访问域名(带组名)
     * @Author Perfree
     * @Date 11:37 2019/6/6
     **/
    public String getShowUrl(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Peers peers = (Peers) request.getSession().getAttribute("peers");
        String showAddress = "";
        if(StringUtil.isBlank(peers.getShowAddress())){
            if(StringUtil.isBlank(peers.getGroupName())){
                showAddress = peers.getServerAddress();
            }else{
                showAddress = peers.getServerAddress()+"/"+peers.getGroupName();
            }
        }else{
            if(StringUtil.isBlank(peers.getGroupName())){
                showAddress = peers.getShowAddress();
            }else{
                showAddress = peers.getShowAddress()+"/"+peers.getGroupName();
            }
        }
        return showAddress;
    }

    /**
     * 获取回显域名(不带url)
     * @Author Perfree
     * @Date 17:30 2019/6/9
     **/
    public String getUploadShowUrl(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Peers peers = (Peers) request.getSession().getAttribute("peers");
        String showAddress = "";
        if(StringUtil.isBlank(peers.getShowAddress())){
            showAddress += peers.getServerAddress();
        }else{
            showAddress += peers.getShowAddress();
        }
        return showAddress;
    }
}
