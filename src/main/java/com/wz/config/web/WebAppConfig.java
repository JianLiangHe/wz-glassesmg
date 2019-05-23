//package com.wz.config.web;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.wz.interceptor.LoginInterceptor;
//
//@Configuration
//public class WebAppConfig implements WebMvcConfigurer {
//
//	private static final Logger LOGGER = Logger.getLogger(WebAppConfig.class);
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		LOGGER.info("注册拦截器");
//		
//		
//		registry.addInterceptor(new LoginInterceptor())
//				.addPathPatterns("/api/**")
//				.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
//				.excludePathPatterns("/api/login/*");
//				;
//				
//	}
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//		 registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
//	}
//	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		// 设置允许跨域访问
//		
//		  registry.addMapping("/**") .allowedMethods("*") .allowedOrigins("*")
//		   .allowedHeaders("*") .allowCredentials(true);
//		 
//		//registry.addMapping("/**").allowedOrigins("http://192.168.18.152:8080").allowCredentials(true).allowedMethods("*").maxAge(3600);
//		
//		LOGGER.info("设置跨域");
//	}
//	
//}
