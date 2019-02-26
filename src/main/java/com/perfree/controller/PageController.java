package com.perfree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perfree.common.GoFastDfsApi;

import cn.hutool.http.HttpUtil;

/**
 * 页面控制
 * @author Perfree
 *
 */
@Controller
public class PageController {

	@Value("${go.fastdfs.server.address}")
	private String serverAddress;
	
	/**
	 * 首页
	 */
	@RequestMapping("/")
	public String index() {
		String json = HttpUtil.get(serverAddress + GoFastDfsApi.STATUS);
		System.out.println(json);
		return "index";
	}
	
	/**
	 * 页面跳转
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}
}
