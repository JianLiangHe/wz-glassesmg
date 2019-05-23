package com.wz.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.wz.util.RtnResult;
import com.wz.util.TokenUtil;
import com.wz.util.WebUtils;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "http://192.168.18.152:8080");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		boolean flag = false;
		response.setCharacterEncoding("UTF8");

		/*
		Cookie[] cookies = request.getCookies();
		
		if (null != cookies) {
			String token = null;

			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					token = cookie.getValue();

					try {
						CustomerToken customerToken = TokenUtil.decryptToken(token);
						
						Long currentTimestamp = new Date().getTime();
						
						Long timestamp = customerToken.getTimestamp();
						
						if (currentTimestamp >= timestamp) {
							returnLoginMessage(response);
						}
						
						flag = true;
					} catch (Exception e) {
						returnLoginMessage(response);
					}
					
					break;
				}
			}
		} else {
			returnLoginMessage(response);
		}
		*/
		String token = request.getHeader("X-Token");
		
		if (null == token) {
			Cookie[] cookies = request.getCookies();
			
			if (null != cookies) {
				for (Cookie cookie : cookies) {
					if ("token".equals(cookie.getName())) {
						token = cookie.getValue();

						try {
							Map<String, Object> customerToken = TokenUtil.decryptToken(token);
							
							Long currentTimestamp = new Date().getTime();
							
							Long timestamp = (Long) customerToken.get("timestamp");
							
							if (currentTimestamp >= timestamp) {
								returnLoginMessage(response);
							}
							
							flag = true;
						} catch (Exception e) {
							returnLoginMessage(response);
						}
						
						break;
					}
				}
			} else {
				returnLoginMessage(response);
			}
		} else {
			try {
				Map<String, Object> customerToken = TokenUtil.decryptToken(token);
				
				Long currentTimestamp = new Date().getTime();
				
				Long timestamp = (Long) customerToken.get("timestamp");
				
				if (currentTimestamp >= timestamp) {
					returnLoginMessage(response);
				}
				
				flag = true;
			} catch (Exception e) {
				returnLoginMessage(response);
			}
		}
		
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	public void returnLoginMessage(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
		out.println(JSON.toJSONString(new RtnResult(WebUtils.ERROR_RESULT, WebUtils.NO_LOGIN_STATUS, WebUtils.ILLEGAL_REQUEST_MESSAGE)));
		out.flush();
		out.close();
	}
	
}
