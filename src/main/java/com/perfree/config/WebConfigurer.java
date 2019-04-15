package com.perfree.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截配置
 * @author Perfree
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer{

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new InstallInterceptor()).excludePathPatterns("/install","/doInstall","/css/**","/images/**","/plugins/**","/fonts/**","/js/**").addPathPatterns("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	}

}
