package com.perfree.controller;

import com.perfree.model.Peers;
import com.perfree.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description controller基类
 * @author Perfree
 * @date 2021/3/23 15:11
 */
@Controller
public class BaseController {

    /**
     * @description 获取已登录用户信息
     * @return com.perfree.model.User
     * @author Perfree
     */
    public User getUser(){
        Subject subject = SecurityUtils.getSubject();
        User user=new User();
        BeanUtils.copyProperties(subject.getPrincipals().getPrimaryPrincipal(), user);
        return user;
    }

    /**
     * @description 获取 HttpServletRequest
     * @return javax.servlet.http.HttpServletRequest
     * @author Perfree
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * @description 获取当前用户使用的集群信息
     * @return com.perfree.model.Peers
     * @author Perfree
     */
    public Peers getPeers(){
        return (Peers) getRequest().getSession().getAttribute("peers");
    }

    /**
     * @description 获取组名
     * @return java.lang.String
     * @author Perfree
     */
    public String getPeersGroupName(){
        Peers peers = (Peers) getRequest().getSession().getAttribute("peers");
        return peers.getGroupName();
    }

    /**
     * @description 获取集群完整管理地址
     * @return java.lang.String
     * @author Perfree
     */
    public String getPeersUrl(){
        Peers peers = (Peers) getRequest().getSession().getAttribute("peers");
        if(!StringUtils.isBlank(peers.getGroupName())) {
            return peers.getServerAddress() + "/" + peers.getGroupName();
        }
        return  peers.getServerAddress();
    }

   /**
    * @description 获取访问域名
    * @return java.lang.String
    * @author Perfree
    */
    public String getShowUrl(){
        Peers peers = (Peers) getRequest().getSession().getAttribute("peers");
        String showAddress = "";
        if(StringUtils.isBlank(peers.getShowAddress())){
            if(StringUtils.isBlank(peers.getGroupName())){
                showAddress = peers.getServerAddress();
            }else{
                showAddress = peers.getServerAddress() + "/" + peers.getGroupName();
            }
        }else{
            if(StringUtils.isBlank(peers.getGroupName())){
                showAddress = peers.getShowAddress();
            }else{
                showAddress = peers.getShowAddress() + "/" + peers.getGroupName();
            }
        }
        return showAddress;
    }

    /**
     * @description 获取回显域名(不带url)
     * @return java.lang.String
     * @author Perfree
     */
    public String getUploadShowUrl(){
        Peers peers = (Peers) getRequest().getSession().getAttribute("peers");
        String showAddress = "";
        if(StringUtils.isBlank(peers.getShowAddress())){
            showAddress += peers.getServerAddress();
        }else{
            showAddress += peers.getShowAddress();
        }
        return showAddress;
    }
}
