package com.wz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wz.dao.CustomerMapper;
import com.wz.dto.FindCustomerDto;
import com.wz.pojo.Customer;
import com.wz.service.CustomerService;
import com.wz.util.RtnResult;
import com.wz.util.WebUtils;
import com.wz.util.enums.CustomerEnum;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public RtnResult get(Integer id) {
		Customer customer = customerMapper.selectByPrimaryKey(id);
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, customer);
	}

	@Override
	public Map<String, Object> find(FindCustomerDto dto) {
		Integer isDelete = CustomerEnum.IsDelete.否.getValue();
		dto.setIsDelete(isDelete);

		// 1, 查询数据
		List<Customer> result = customerMapper.find(dto);
		
		int totalSize = customerMapper.getCount(dto);
		
		Map<String, Object> resultMap = new HashMap<String, Object>(){{
			put("rows", result);
			put("total", totalSize);
			put("status","200");
			put("msg","success");
		}};
		
		return resultMap;
	}

	@Override
	public RtnResult updateStatus(Integer id, Integer status) {
		Customer customer = customerMapper.selectByPrimaryKey(id);
		
		if (customer == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该用户不存在！");
		}

		try {
			customerMapper.updateStatus(id, status);
			
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更改用户状态失败！");
		}
	}

	@Override
	public RtnResult regist(Customer customer) {
		String password = customer.getPassword();
		
		customerMapper.insertSelective(customer);
		
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
	}

	@Override
	public RtnResult registApprove(Integer id, Integer approveStatus) {
		Customer customer = customerMapper.selectByPrimaryKey(id);
		
		if (customer == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该用户不存在！");
		}

		if (customer.getApproveStatus() != 0) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该用户已经审批过了！");
		}

		try {
			customerMapper.registApprove(id, approveStatus);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, "审批成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "审批失败！");
		}
	}

	@Override
	public RtnResult getProductList() {
		List<Customer>	produceList = customerMapper.getProduceList();
		
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, produceList);
	}
}
