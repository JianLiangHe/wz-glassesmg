package com.wz.service;

import com.wz.pojo.Role;
import com.wz.util.RtnResult;

public interface RoleService {
	
	RtnResult save(Role role);
	
	RtnResult update(Role role);
	
	RtnResult getRoleAndPower();
	
	RtnResult delete(Integer id);

}
