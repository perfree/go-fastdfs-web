package com.perfree.config;

import com.perfree.interceptor.InstallInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description Web配置-静态资源配置
 * @author Perfree
 * @date 2021/3/22 13:41
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public InstallInterceptor installInterceptor() {
        return new InstallInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(installInterceptor()).excludePathPatterns("/install","/install/**","/static/**").addPathPatterns("/**");
    }
}
