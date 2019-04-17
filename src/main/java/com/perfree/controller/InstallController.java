package com.perfree.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.perfree.common.*;
import com.perfree.entity.User;
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
	/** server地址配置key */
	private static String PROPERTY_NAME = "go.fastdfs.server.address";
	/** server地址默认value */
	private static String SERVER_DEFAULT = "default";

	@Autowired
	private InstallService installService;
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	/**
	 * 安装页
	 * @return String
	 */
	@RequestMapping("/install")
	public String index() {
		String serverAddress = propertiesUtil.getProperty(PROPERTY_NAME);
		if(StringUtil.isBlank(serverAddress) || !serverAddress.equals(SERVER_DEFAULT)) {
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
	public AjaxResult doInstall(@Valid User user,BindingResult bindingResult,String server) {
		if(bindingResult.hasErrors()){
			return new AjaxResult(AjaxResult.AJAX_ERROR,bindingResult.getFieldError().getDefaultMessage());
		}
		//校验服务地址
		if(!RegexUtill.verifyUrl(server)){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"go-fastdfs服务地址填写错误!");
		}
		try{
			String string = HttpUtil.get(server+GoFastDfsApi.STAT);
			JSONObject parseObj = JSONUtil.parseObj(string);
			if(!parseObj.get("status").equals("ok")) {
				return new AjaxResult(AjaxResult.AJAX_ERROR,"连接go-fastdfs服务失败!请检查服务地址是否已配置白名单!");
			}
		}catch(Exception e){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"连接go-fastdfs服务失败!请检查服务地址是否正确!");
		}
		String serverAddress = propertiesUtil.getProperty(PROPERTY_NAME);
		if(StringUtil.isBlank(serverAddress) || !serverAddress.equals(SERVER_DEFAULT)) {
			return new AjaxResult(AjaxResult.AJAX_ERROR,"您已安装!");
		}
		return installService.install(user, server);
	}
}
