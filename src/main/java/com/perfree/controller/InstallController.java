package com.perfree.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.perfree.common.*;
import com.perfree.entity.User;
import com.perfree.mapper.UserMapper;
import com.perfree.service.InstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 安装
 * @author Perfree
 */
@Controller
public class InstallController {

	@Autowired
	private InstallService installService;
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * 安装页
	 * @return String
	 */
	@RequestMapping("/install")
	public String index() {
		if(userMapper.getUserCount() >= 1) {
			return "redirect:/";
		}
		return "install";
	}
	
	/**
	 * 执行安装操作
	 * @param user
	 * @param server
	 * @return AjaxResult
	 */
	@RequestMapping("/doInstall")
	@ResponseBody
	@Validated
	public AjaxResult doInstall(@Valid User user,BindingResult bindingResult,String serverName,String groupName,String server,String showAddress) {
		if(bindingResult.hasErrors()){
			return new AjaxResult(AjaxResult.AJAX_ERROR,bindingResult.getFieldError().getDefaultMessage());
		}
		if(userMapper.getUserCount() >= 1){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"您已安装!请直接登录!");
		}
		if(StrUtil.isBlank(serverName) || serverName.length() > 100){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"请正确填写集群名称(100字符以内)");
		}
		if(StrUtil.isBlank(server) || server.length() > 100){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"请正确填写服务地址(100字符以内)");
		}
		//校验服务地址
		if(!RegexUtill.verifyUrl(server)){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"go-fastdfs服务地址填写错误!");
		}
		try{
			String urlPath = server;
			if(StrUtil.isNotBlank(groupName)){
				urlPath+="/"+groupName;
			}
			String string = HttpUtil.get(urlPath+GoFastDfsApi.STAT);
			JSONObject parseObj = JSONUtil.parseObj(string);
			if(!parseObj.get("status").equals("ok")) {
				return new AjaxResult(AjaxResult.AJAX_ERROR,"连接go-fastdfs服务失败!请检查服务地址是否已配置白名单!");
			}
		}catch(Exception e){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"连接go-fastdfs服务失败!请检查服务地址是否正确!");
		}
		return installService.install(user,serverName,groupName,server,showAddress);
	}
}
