package com.perfree.controller;


import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.perfree.common.AjaxResult;
import com.perfree.entity.User;

/**
 * 系统处理controller,主要处理登录等系统级别的逻辑
 * @author Perfree
 *
 */
@Controller
public class SystemController {
	
	private static Logger logger = Logger.getLogger(SystemController.class);
	
	/**
	 * 登录页
	 * @return String
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * 登录操作
	 * @param user
	 * @return AjaxResult
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,path="/doLogin")
	public AjaxResult doLogin(User user,Boolean rememberMe) {
		AjaxResult result = null;
		if(rememberMe == null) {
            rememberMe = false;
        }
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getAccount(),user.getPassword(),rememberMe);
			Subject subject = SecurityUtils.getSubject();
			subject.login(usernamePasswordToken);
			result = new AjaxResult(AjaxResult.AJAX_SUCCESS);
			logger.info(user.getAccount()+" >>>login");
		}catch (IncorrectCredentialsException e) {
			logger.info(user.getAccount()+e.getMessage());
			result = new AjaxResult(AjaxResult.AJAX_ERROR,"密码错误");
		}catch (UnknownAccountException e) {
			logger.info(user.getAccount()+e.getMessage());
			result = new AjaxResult(AjaxResult.AJAX_ERROR,"用户不存在");
		}catch (Exception e) {
			logger.error(user.getAccount()+e.getMessage());
			result = new AjaxResult(AjaxResult.AJAX_ERROR,"系统异常");
		}
		return result;
	}
	
	/**
	 * 登出
	 * @return String
	 */
	@RequestMapping(path="/logout",method=RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
        User user=new User();
        BeanUtils.copyProperties(subject.getPrincipals().getPrimaryPrincipal(), user);
        logger.info(user.getAccount()+" >>>logout");
        subject.logout();
		return "redirect:/login";
	}
}
