package com.wz.dao;

import java.util.List;

import com.wz.dto.FindCustomerDto;
import com.wz.pojo.Customer;

import io.lettuce.core.dynamic.annotation.Param;

public interface CustomerMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Customer record);

	int insertSelective(Customer record);

	Customer selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Customer record);

	int updateByPrimaryKey(Customer record);

	/**
	 * 查询
	 * @param dto
	 * @return
	 */
	List<Customer> find(@Param("dto") FindCustomerDto dto);

	/**
	 * 获取数据条数
	 * @param dto
	 * @return
	 */
	int getCount(@Param("dto") FindCustomerDto dto);

	/**
	 * ssds
	 * @param id
	 * @param status
	 * @return
	 */
	int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

	// 审批用户注册
	int registApprove(@Param("id") Integer id, @Param("approveStatus") Integer approveStatus);

	// 查找用户购买的相关商品
	List<Customer> getProduceList();
}