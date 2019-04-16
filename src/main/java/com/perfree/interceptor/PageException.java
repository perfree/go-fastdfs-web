package com.perfree.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 全局异常拦截
 * @author Perfree
 *
 */

public class PageException implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		// 获取statusCode:401,404,500
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == 401) {
			return "/401";
		} else if (statusCode == 404) {
			return "/404";
		} else if (statusCode == 403) {
			return "/403";
		} else {
			return "/500";
		}

	}

}
