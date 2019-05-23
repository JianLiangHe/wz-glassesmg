package com.wz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wz.pojo.Role;
import com.wz.service.RoleService;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "角色权限接口")
@RestController
@RequestMapping(value = "/api/role/")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@ApiOperation(value = "添加角色及对应的权限")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public RtnResult add(
			@ApiParam(value = "name", required = true) @RequestParam(value = "name", required = true) String name,
			@ApiParam(value = "权限", required = true) @RequestParam(value = "powerJson", required = true) String powerJson) {
		Role role = new Role();
		role.setName(name);
		role.setPowerJson(powerJson);
		return roleService.save(role);
	}

	@ApiOperation(value = "修改角色及对应的权限")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public RtnResult update(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "name", required = false) @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "权限", required = false) @RequestParam(value = "powerJson", required = false) String powerJson) {

		Role role = new Role();
		role.setId(id);
		role.setName(name);
		role.setPowerJson(powerJson);
		return roleService.update(role);
	}

	@ApiOperation(value = "查看角色及对应的权限")
	@RequestMapping(value = "getRoleAndPower", method = RequestMethod.POST)
	public RtnResult getRoleAndPower() {
		return roleService.getRoleAndPower();
	}
	
	@ApiOperation(value = "删除角色及对应的权限")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public RtnResult delete(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id
			) {
		return roleService.delete(id);
	}

}
