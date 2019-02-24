package com.perfree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制
 * @author Perfree
 *
 */
@Controller
public class PageController {

	/**
	 * 首页
	 */
	@RequestMapping("/")
	public String index() {
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
