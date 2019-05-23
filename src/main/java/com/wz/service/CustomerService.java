package com.wz.service;

import com.wz.dto.FindCustomerDto;
import com.wz.dto.FindUserDto;
import com.wz.pojo.Customer;
import com.wz.util.RtnResult;

public interface CustomerService {

	RtnResult get(Integer id);

	RtnResult find(FindCustomerDto dto);

	RtnResult updateStatus(Integer id, Integer status);
	
	RtnResult regist(Customer customer);
	
	RtnResult registApprove(Integer id, Integer approveStatus);
}
