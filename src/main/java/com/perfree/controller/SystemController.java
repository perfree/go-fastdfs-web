package com.perfree.controller;

import com.perfree.common.ResponseBean;
import com.perfree.model.User;
import com.perfree.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description 常用Controller,包含首页,登录,退出等
 * @author Perfree
 * @date 2021/3/22 13:44
 */
@Controller
public class SystemController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private UserService userService;

    /**
     * @description 登录页
     * @return java.lang.String
     * @author Perfree
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * @description 登录
     * @param user  user
     * @return com.perfree.common.ResponseBean
     * @author Perfree
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public ResponseBean doLogin(User user) {
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getAccount(),user.getPassword(), false);
            Subject subject = SecurityUtils.getSubject();
            subject.login(usernamePasswordToken);
            LOGGER.info("{} >>>login", user.getAccount());
            return ResponseBean.success();
        }catch (IncorrectCredentialsException e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return ResponseBean.fail("密码错误");
        }catch (UnknownAccountException e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return ResponseBean.fail("用户不存在");
        }catch (Exception e) {
            LOGGER.info(user.getAccount() + e.getMessage());
            return ResponseBean.fail("系统异常");
        }
    }

    /**
     * @description 退出登录
     * @return java.lang.String
     * @author Perfree
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        User user = new User();
        BeanUtils.copyProperties(subject.getPrincipals().getPrimaryPrincipal(), user);
        LOGGER.info("{} >>>logout", user.getAccount());
        subject.logout();
        return "redirect:/login";
    }
}
