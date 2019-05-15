package com.wz.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CookieUtil {

	private static final Logger LOGGER = Logger.getLogger(CookieUtil.class);
	
	public static void saveCookieByToken(String token) {
		ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = servlet.getResponse();
		response.setCharacterEncoding("utf-8");
		
		Cookie cookie = new Cookie("token", token);
		cookie.setPath(PropertieUtil.getProperty("APP_COOKIE_PATH"));
		cookie.setDomain(PropertieUtil.getProperty("APP_COOKIE_DOMAIN"));
		cookie.setHttpOnly(Boolean.parseBoolean(PropertieUtil.getProperty("APP_COOKIE_HTTP_ONLY")));
		cookie.setMaxAge(Integer.parseInt(PropertieUtil.getProperty("APP_COOKIE_MAX_AGE")));
		
		response.addCookie(cookie);
		
		LOGGER.info("保存cookie成功, token[" + token + "]");
	}
	
	/**
	 * 获取cookie里的token
	 * @return
	 */
	public static String getToken() {
		ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servlet.getRequest();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String token = null;
		
		Cookie[] cookies = request.getCookies();
		
		if (null == cookies || cookies.length <= 0) {
			return null;
		}
		
		for (Cookie c : cookies) {
			if ("token".equals(c.getName())) {
				token = c.getValue();
			}
		}
		
		return token;
	}
	
	/**
	 * 清除token
	 */
	public static void clearCookie() {
		ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = servlet.getResponse();
		response.setCharacterEncoding("utf-8");
		
		Cookie cookie = new Cookie("token", null);
		cookie.setPath(PropertieUtil.getProperty("APP_COOKIE_PATH"));
		cookie.setDomain(PropertieUtil.getProperty("APP_COOKIE_DOMAIN"));
		cookie.setHttpOnly(Boolean.parseBoolean(PropertieUtil.getProperty("APP_COOKIE_HTTP_ONLY")));
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
}
