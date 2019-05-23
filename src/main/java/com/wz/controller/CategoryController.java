package com.wz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wz.pojo.Category;
import com.wz.pojo.Page;
import com.wz.service.CategoryService;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "类目接口")
@RestController
@RequestMapping("/api/Category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "查找类目")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public RtnResult find(@ModelAttribute Page page) {
		return categoryService.find(page);
	}

	@ApiOperation(value = "添加类目")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public RtnResult save(
			@ApiParam(value = "name", required = true) @RequestParam(value = "name", required = true) String name,
			@ApiParam(value = "orderNo", required = true) @RequestParam(value = "orderNo", required = true) Integer orderNo,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = true) @RequestParam(value = "status", required = true) Integer status) {
		Category category = new Category();
		category.setName(name);
		category.setOrderNo(orderNo);
		category.setStatus(status);
		return categoryService.add(category);
	}
	
	@ApiOperation(value = "更新类目")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public RtnResult update(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "name", required = false) @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "orderNo", required = false) @RequestParam(value = "orderNo", required = false) Integer orderNo,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = false) @RequestParam(value = "status", required = false) Integer status) {
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		category.setOrderNo(orderNo);
		category.setStatus(status);
		return categoryService.update(category);
	}

	
	@ApiOperation(value = "更新类目(正常或停用)")
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public RtnResult updateStatus(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = true) @RequestParam(value = "status", required = true) Integer status
			) {
		return categoryService.updateStatus(id, status);
	}
}
