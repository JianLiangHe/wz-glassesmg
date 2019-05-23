package com.wz.service;

import com.wz.util.RtnResult;

/**
 * 登陆业务层
 * @author Administrator
 *
 */
public interface LoginService {

	/**
	 * 管理员登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	RtnResult loginByUser(String userName, String password);
	
}
