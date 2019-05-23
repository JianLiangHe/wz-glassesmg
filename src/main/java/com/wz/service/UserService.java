package com.wz.service;

import com.wz.dto.FindUserDto;
import com.wz.pojo.User;
import com.wz.util.RtnResult;

public interface UserService {

	RtnResult get();
	
	RtnResult find(FindUserDto dto);
	
	RtnResult update(User user);
	
	RtnResult regist(User user);
	
	RtnResult changePassword(String username,String password);
	
	Integer getCurrentUserId();
	
}
