package com.wz.service;

import javax.servlet.http.HttpServletRequest;

import com.wz.util.RtnResult;

/**
 * 登陆业务层
 * @author Administrator
 *
 */
public interface LoginService {

	/**
	 * 管理员登陆
	 * @param request
	 * @param userName
	 * @param password
	 * @return
	 */
	RtnResult loginByUser(HttpServletRequest request, String userName, String password);
	
}
