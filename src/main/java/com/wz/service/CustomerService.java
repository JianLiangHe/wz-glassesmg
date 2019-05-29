package com.wz.service;

import java.util.Map;

import com.wz.dto.FindCustomerDto;
import com.wz.pojo.Customer;
import com.wz.util.RtnResult;

public interface CustomerService {

	RtnResult get(Integer id);

	Map<String, Object> find(FindCustomerDto dto);

	RtnResult updateStatus(Integer id, Integer status);
	
	RtnResult regist(Customer customer);
	
	RtnResult registApprove(Integer id, Integer approveStatus);
	
	RtnResult getProductList();
}
