package com.wz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wz.dao.RoleMapper;
import com.wz.pojo.Role;
import com.wz.service.RoleService;
import com.wz.util.RtnResult;
import com.wz.util.WebUtils;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public RtnResult save(Role role) {
		try {
			roleMapper.insertSelective(role);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "添加角色失败！");
		}
	}

	@Override
	public RtnResult update(Role role) {

		Integer id = role.getId();
		Role r = roleMapper.selectByPrimaryKey(id);

		if (r == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "找不到该角色！");
		}
		try {
			roleMapper.updateByPrimaryKeySelective(role);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新角色失败！");
		}
	}

	@Override
	public RtnResult getRoleAndPower() {

		List<Role> resultList = roleMapper.find();
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, resultList);
	}

	@Override
	public RtnResult delete(Integer id) {

		Role r = roleMapper.selectByPrimaryKey(id);

		if (r == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "找不到该角色！");
		}

		try {
			roleMapper.deleteByPrimaryKey(id);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "删除角色失败！");
		}
	}

}
