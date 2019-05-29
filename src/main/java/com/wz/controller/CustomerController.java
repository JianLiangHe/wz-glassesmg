package com.wz.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wz.dto.FindCustomerDto;
import com.wz.pojo.Customer;
import com.wz.service.CustomerService;
import com.wz.util.PageQueryUtil;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "toCustomer.do", method = RequestMethod.GET)
    public ModelAndView toCustomer() {
    	ModelAndView view = new ModelAndView();
    	view.setViewName("view/customer/index");
    	
    	return view;
    }
    
    @ApiOperation(value = "查找用户")
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public RtnResult save(
            @ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id
    ) {
        return customerService.get(id);
    }
    
    @ApiOperation(value = "查找用户(条件)")
	@RequestMapping(value = "find", method = RequestMethod.GET)
	public String find(
			@ApiParam(value = "id", required = false) @RequestParam(value = "id", required = false) Integer id,
			@ApiParam(value = "店铺名", required = false) @RequestParam(value = "shopName", required = false) String shopName,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = false) @RequestParam(value = "status", required = false) Integer status,
			@ApiParam(value = "城市", required = false) @RequestParam(value = "city", required = false) String city,
			@ApiParam(value = "电话", required = false) @RequestParam(value = "mobile", required = false) String mobile,
			@ApiParam(value = "审核状态(0:未审核,1:审批通过,2:审批不通过)", required = false) @RequestParam(value = "approveStatus", required = false) Integer approveStatus,
			@ModelAttribute PageQueryUtil page,
			HttpServletRequest request,
			HttpServletResponse response
			) {
		FindCustomerDto dto = new FindCustomerDto();
		dto.setApproveStatus(approveStatus);
		dto.setCity(city);
		dto.setMobile(mobile);
		dto.setShopName(shopName);
		dto.setId(id);
		dto.setStatus(status);
		dto.setPage(page);
		
		Map<String, Object> resultMap = customerService.find(dto);
		
		return JSON.toJSONStringWithDateFormat(resultMap, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@ApiOperation(value = "注销用户(更改用户状态)")
	@RequestMapping(value = "cancel", method = RequestMethod.POST)
	public RtnResult updateStatus(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "status", required = true) @RequestParam(value = "status", required = true) Integer status
			
			) {
		return customerService.updateStatus(id, status);
	}
	
	@ApiOperation(value = "用户注册)")
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public RtnResult regist(
			@ApiParam(value = "店铺名", required = true) @RequestParam(value = "shopName", required = true) String shopName,
			@ApiParam(value = "省份", required = true) @RequestParam(value = "province", required = true) String province,
			@ApiParam(value = "城市", required = true) @RequestParam(value = "city", required = true) String city,
			@ApiParam(value = "区域", required = true) @RequestParam(value = "area", required = true) String area,
			@ApiParam(value = "地址", required = true) @RequestParam(value = "address", required = true) String address,
			@ApiParam(value = "联系人", required = true) @RequestParam(value = "linkman", required = true) String linkman,
			@ApiParam(value = "密码", required = true) @RequestParam(value = "password", required = true) String password,
			@ApiParam(value = "身份证url", required = true) @RequestParam(value = "idcardUrl", required = true) String idcardUrl,
			@ApiParam(value = "执照url", required = true) @RequestParam(value = "businessUrl", required = true) String businessUrl,
			@ApiParam(value = "手机号",required = true) @RequestParam(value = "mobile", required = true) String mobile
			) {
		Customer customer = new Customer();
		customer.setAddress(address);
		customer.setArea(area);
		customer.setBusinessUrl(businessUrl);
		customer.setCity(city);
		customer.setIdcardUrl(idcardUrl);
		customer.setLinkman(linkman);
		customer.setMobile(mobile);
		customer.setPassword(password);
		customer.setProvince(province);
		customer.setShopName(shopName);
		
		return customerService.regist(customer);
	}
	
	@ApiOperation(value = "用户注册审批")
	@RequestMapping(value = "registApprove", method = RequestMethod.POST)
	public RtnResult registApprove(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "审核状态(1:审批通过,2:审批不通过)", required = true) @RequestParam(value = "approveStatus", required = true) Integer approveStatus
			) {
		return customerService.registApprove(id, approveStatus);
	}
	
	@ApiOperation(value = "查询用户购买的商品")
	@RequestMapping(value = "findProduct", method = RequestMethod.POST)
	public RtnResult findProduct(
			
			) {
		return customerService.getProductList();
	}

}
