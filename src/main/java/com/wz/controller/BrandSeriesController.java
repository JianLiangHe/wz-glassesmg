package com.wz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wz.pojo.BrandSeries;
import com.wz.pojo.BrandType;
import com.wz.pojo.Page;
import com.wz.service.BrandSeriesService;
import com.wz.service.BrandTypeService;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "品牌系列接口")
@RestController
@RequestMapping("/api/BrandSeries/")
public class BrandSeriesController {
	
	@Autowired
	private BrandSeriesService brandSeriesService;
	
	@ApiOperation(value = "查找品牌系列")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public RtnResult find(@ModelAttribute Page page) {
		return brandSeriesService.find(page);
	}

	@ApiOperation(value = "添加品牌系列")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public RtnResult save(
			@ApiParam(value = "name", required = true) @RequestParam(value = "name", required = true) String name,
			@ApiParam(value = "品牌id", required = true) @RequestParam(value = "brandId", required = true) Integer brandId,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = true) @RequestParam(value = "status", required = false) Integer status,
			@ApiParam(value = "筛选条件json", required = true) @RequestParam(value = "screenJson", required = false) String screenJson
			) {
		BrandSeries brandSeries = new BrandSeries();
		brandSeries.setName(name);
		brandSeries.setStatus(status);
		brandSeries.setScreenJson(screenJson);
		brandSeries.setBrandId(brandId);
		return brandSeriesService.add(brandSeries);
	}
	
	@ApiOperation(value = "更新品牌系列")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public RtnResult update(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "name", required = false) @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "品牌id", required = false) @RequestParam(value = "brandId", required = false) Integer brandId,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = false) @RequestParam(value = "status", required = false) Integer status,
			@ApiParam(value = "筛选条件json", required = false) @RequestParam(value = "screenJson", required = false) String screenJson
			
			) {
		BrandSeries brandSeries = new BrandSeries();
		brandSeries.setId(id);
		brandSeries.setName(name);
		brandSeries.setBrandId(brandId);
		brandSeries.setStatus(status);
		brandSeries.setScreenJson(screenJson);
		return brandSeriesService.update(brandSeries);
	}

	
	@ApiOperation(value = "更新品牌系列(正常或停用)")
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public RtnResult updateStatus(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = true) @RequestParam(value = "status", required = true) Integer status
			) {
		return brandSeriesService.updateStatus(id, status);
	}

}
