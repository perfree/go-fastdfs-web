package com.perfree.controller;

import com.perfree.common.AjaxResult;
import com.perfree.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perfree.common.FileSizeUtil;
import com.perfree.common.GoFastDfsApi;
import com.perfree.common.PropertiesUtil;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 首页
 * @author Perfree
 *
 */
@Controller
public class IndexController {

	@Autowired
	private PropertiesUtil propertiesUtil;

	@Autowired
	private IndexService indexService;

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * 控制台
	 * @return
	 */
	@RequestMapping("/main")
	public String main(Model model) {
		return "main";
	}

	@RequestMapping("/main/getStat")
	@ResponseBody
	public AjaxResult getStat(){
		String serverAddress = propertiesUtil.getProperty("go.fastdfs.server.address");
		try {
			//获取文件信息,这一部分有待优化
			String string = HttpUtil.get(serverAddress+GoFastDfsApi.STAT);
			JSONObject parseObj = JSONUtil.parseObj(string);
			if(parseObj.get("status").equals("ok")) {
				Map<String, Object> result = indexService.getfileStat(parseObj.get("data"));
				return new AjaxResult(AjaxResult.AJAX_SUCCESS,result);
			}else{
				return new AjaxResult(AjaxResult.AJAX_ERROR,"调取go-fastdfs接口失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(AjaxResult.AJAX_ERROR,"系统异常");
	}
}
