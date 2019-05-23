package com.wz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wz.dao.RoleMapper;
import com.wz.dao.UserMapper;
import com.wz.dto.FindUserDto;
import com.wz.pojo.Role;
import com.wz.pojo.User;
import com.wz.service.UserService;
import com.wz.util.CookieUtil;
import com.wz.util.MD5Util;
import com.wz.util.RtnResult;
import com.wz.util.TokenUtil;
import com.wz.util.WebUtils;
import com.wz.util.enums.StatusEnum;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	private static final String DEFAULT_PASSWORD = MD5Util.string2MD5("wz888888");

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public RtnResult get() {

		ServletRequestAttributes servlet = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servlet.getRequest();
//		String token = request.getHeader("X-Token");

		try {
//			Map<String, Object> user = TokenUtil.decryptToken(token);
//			Integer id = (Integer) user.get("id");
			Integer id = 1;
			User u = userMapper.getUserInfo(id);

			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, u);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.NO_LOGIN_STATUS, "token已过期, 请重新登陆");
		}
	}

	@Override
	public RtnResult find(FindUserDto dto) {
		List<User> resultList = userMapper.userLists(dto);
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, resultList);
	}

	@Override
	public RtnResult update(User user) {

		Integer id = user.getId();
		User u = userMapper.selectByPrimaryKey(id);
		if (u == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该用户不存在！");
		}

		try {
			userMapper.updateByPrimaryKeySelective(user);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更改用户状态失败！");
		}
	}

	@Override
	public RtnResult regist(User user) {

		String userName = user.getUsername();
		String password;

		// 1, 判断该用户名是否存在
		User u = userMapper.getByName(userName);
		if (u != null) {
			LOGGER.info("新增管理员失败, 用户名[" + userName + "]已经存在.");

			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该用户名已经存在, 新增失败");
		}

		// 2, 判断密码
		password = user.getPassword();

		if (null == password || password.length() <= 0) {
			// 设置默认密码
			password = DEFAULT_PASSWORD;
			user.setPassword(password);
		} else if (password.length() < 6 || password.length() > 20) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "密码长度范围(6 - 20)位");
		} else {
			user.setPassword(MD5Util.string2MD5(password));
		}
		try {
			// 新增
			userMapper.insertSelective(user);

			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			LOGGER.info("新增用户失败, 参数有误, 用户名[" + userName + "]");

			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "新增管理员失败, 参数有误");
		}
	}

	@Override
	public RtnResult changePassword(String username, String password) {

		// 1, 判断该用户名是否存在
		User u = userMapper.getByName(username);
		if (u == null) {
			LOGGER.info("找不到该管理员.");
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "找不到管理员");
		}

		if (null == password || password.length() <= 0) {
			// 设置默认密码
			password = DEFAULT_PASSWORD;
		} else if (password.length() < 6 || password.length() > 20) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "密码长度范围(6 - 20)位");
		} else {
			password = MD5Util.string2MD5(password);
		}

		try {
			userMapper.changePassword(username, password);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "修改密码失败！");
		}

	}

	@Override
	public Integer getCurrentUserId() {
	
		String token = CookieUtil.getToken();

		Integer id = null;
		try {
			Map<String, Object> user = TokenUtil.decryptToken(token);

				id = (null == user ? null : (Integer) user.get("id"));
		} catch (Exception e) {
			LOGGER.error("管理员没有登陆,获取不了管理员id, error message: " + e.getMessage());
		} finally {
			return id;
		}
	}
}
