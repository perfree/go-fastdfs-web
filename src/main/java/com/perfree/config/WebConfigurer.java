package com.perfree.config;

import org.springframework.context.annotation.Bean;
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

	/**
	 * 因springboot拦截器使用@Value获取不到值,故将此类交由springboot管理
	 * @return
	 */
	@Bean
	public InstallInterceptor installInterceptor() {
		return new InstallInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(installInterceptor()).excludePathPatterns("/install","/doInstall","/css/**","/images/**","/plugins/**","/fonts/**","/js/**").addPathPatterns("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	}

}
