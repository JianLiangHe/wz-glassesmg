package com.wz.controller;

import org.apache.commons.digester.plugins.strategies.FinderFromClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wz.dto.FindUserDto;
import com.wz.pojo.Page;
import com.wz.pojo.User;
import com.wz.service.UserService;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "管理员接口")
@RestController
@RequestMapping("/api/User/")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "获取管理员信息(自身)")
	@RequestMapping(value = "get", method = RequestMethod.POST)
	public RtnResult get() {
		return userService.get();
	}
	
	@ApiOperation(value = "查找管理员(条件)")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public RtnResult find(
			@ApiParam(value = "id", required = false) @RequestParam(value = "id", required = false) Integer id,
			@ApiParam(value = "username", required = false) @RequestParam(value = "username", required = false) String username,
			@ApiParam(value = "status", required = false) @RequestParam(value = "status", required = false) Integer status,
			@ModelAttribute Page page
			) {
		FindUserDto dto = new FindUserDto();
		dto.setId(id);
		dto.setStatus(status);
		dto.setUsername(username);
		dto.setPage(page);

		return userService.find(dto);
	}
	
	@ApiOperation(value = "修改管理员(更改管理员状态或角色)")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public RtnResult updateStatus(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "status", required = false) @RequestParam(value = "status", required = false) Integer status,
			@ApiParam(value = "roleId", required = false) @RequestParam(value = "roleId", required = false) Integer roleId
			) {
		User user = new User();
		user.setId(id);
		user.setRoleId(roleId);
		user.setStatus(status);
		return userService.update(user);
	}

	
	@ApiOperation(value = "修改密码")
	@RequestMapping(value = "changePassword ", method = RequestMethod.POST)
	public RtnResult changePassword(
			@ApiParam(value = "username", required = true) @RequestParam(value = "username", required = true) String username,
			@ApiParam(value = "password", required = true) @RequestParam(value = "password", required = true) String password
			) {
		return userService.changePassword(username, password);
	}
	
	
	@ApiOperation(value = "管理员注册")
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public RtnResult regist(
			@ApiParam(value = "用户名", required = true) @RequestParam(value = "username", required = true) String username,
			@ApiParam(value = "密码", required = false) @RequestParam(value = "password", required = false) String password,
//			@ApiParam(value = "状态(0:正常,1:注销)", required = true) @RequestParam(value = "status", required = true) Integer status,
			@ApiParam(value = "角色id", required = true) @RequestParam(value = "roleId", required = true) Integer roleId
			) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
//		user.setStatus(status);
		user.setRoleId(roleId);
		return userService.regist(user);
	}
	

}
