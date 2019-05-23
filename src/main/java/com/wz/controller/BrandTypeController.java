package com.wz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wz.pojo.Brand;
import com.wz.pojo.BrandType;
import com.wz.pojo.Page;
import com.wz.service.BrandTypeService;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "品牌类型接口")
@RestController
@RequestMapping("/api/BrandType/")
public class BrandTypeController {
	
	@Autowired
	private BrandTypeService brandTypeService;
	
	@ApiOperation(value = "查找品牌类型")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public RtnResult find(@ModelAttribute Page page) {
		return brandTypeService.find(page);
	}

	@ApiOperation(value = "添加品牌类型")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public RtnResult save(
			@ApiParam(value = "name", required = true) @RequestParam(value = "name", required = true) String name,
			@ApiParam(value = "类目id", required = true) @RequestParam(value = "categoryId", required = true) Integer categoryId,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = true) @RequestParam(value = "status", required = false) Integer status
			) {
		BrandType brandType = new BrandType();
		brandType.setStatus(status);
		brandType.setName(name);
		brandType.setCategoryId(categoryId);
		return brandTypeService.add(brandType);
	}
	
	@ApiOperation(value = "更新品牌类型")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public RtnResult update(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "name", required = false) @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "类目id", required = false) @RequestParam(value = "categoryId", required = false) Integer categoryId,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = false) @RequestParam(value = "status", required = false) Integer status) {
		BrandType brandType = new BrandType();
		brandType.setStatus(status);
		brandType.setName(name);
		brandType.setCategoryId(categoryId);
		brandType.setId(id);
		return brandTypeService.update(brandType);
	}

	
	@ApiOperation(value = "更新品牌(正常或停用)")
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public RtnResult updateStatus(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = true) @RequestParam(value = "status", required = true) Integer status
			) {
		return brandTypeService.updateStatus(id, status);
	}

}
