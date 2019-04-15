package com.perfree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author Perfree
 *
 */
@Controller
public class IndexController {

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
	public String main() {
		return "main";
	}
}
