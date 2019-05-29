package com.wz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wz.service.LoginService;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "登陆接口")
@RestController
@RequestMapping(value = "/api/login/")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@ApiOperation(value = "管理员登陆")
	@RequestMapping(value = "loginByUser", method = RequestMethod.POST)
	public RtnResult loginByUser(
			HttpServletRequest request,
			@ApiParam(value = "用户名", required = true) @RequestParam(value = "userName", required = true) String userName,
			@ApiParam(value = "密码", required = true) @RequestParam(value = "password", required = true) String password
	) {
		return loginService.loginByUser(request, userName, password);
	}
	
}
