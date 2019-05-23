package com.wz.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wz.dao.UserMapper;
import com.wz.pojo.User;
import com.wz.service.LoginService;
import com.wz.util.CookieUtil;
import com.wz.util.DateUtil;
import com.wz.util.MD5Util;
import com.wz.util.RtnResult;
import com.wz.util.TokenUtil;
import com.wz.util.WebUtils;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Override
	public RtnResult loginByUser(String username, String password) {
		// 1, 判断用户名是否存在
		User dbUser = userMapper.getByName(username);

		if (null == dbUser) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "用户不存在");
		}

		// 2, 校验密码
		if (password.length() < 6 || password.length() > 20) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "密码长度范围:(6 - 20)位");
		}

		String dbUserPassword = dbUser.getPassword();
		password = MD5Util.string2MD5(password);

		if (!dbUserPassword.equals(password)) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "密码不正确");
		}

		try {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", dbUser.getId());
			
			Long timestamp = DateUtil.getBeforeOrAfterTimestamp(new Date(), 7);
			data.put("timestamp", timestamp);
			
			String token = TokenUtil.encryptToken(data);
			CookieUtil.saveCookieByToken(token);
			
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, token);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "登录失败！");
		}
	}

}
