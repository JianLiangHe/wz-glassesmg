package com.wz.config.web;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

	private static final Logger LOGGER = Logger.getLogger(WebAppConfig.class);
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LOGGER.info("注册拦截器");
		
		// 注册登陆拦截器
		/*
		 * registry.addInterceptor(new LoginInterceptor()) .addPathPatterns("/**")
		 * .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**",
		 * "/swagger-ui.html/**") .excludePathPatterns("/api/index/*")
		 * .excludePathPatterns("/api/customer/register", "/api/customer/login",
		 * "/api/customer/loginByPassword")
		 * .excludePathPatterns("/api/verificationCode/*")
		 * .excludePathPatterns("/api/lawyer/*") .excludePathPatterns("/api/news/*")
		 * .excludePathPatterns("/api/weChatPay/*") .excludePathPatterns("/api/order/*")
		 * .excludePathPatterns("/api/case/approveCallback")
		 * .excludePathPatterns("/api/pushMsg/*") .excludePathPatterns("/api/article/*")
		 * .excludePathPatterns("/api/weChatPay/*")
		 * .excludePathPatterns("/templates/article/js");
		 */
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		 registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
	}

}
