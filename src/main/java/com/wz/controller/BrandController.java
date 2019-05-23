package com.wz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wz.pojo.Brand;
import com.wz.pojo.Category;
import com.wz.pojo.Page;
import com.wz.service.BrandService;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "眼镜品牌接口")
@RestController
@RequestMapping("/api/Brand/")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@ApiOperation(value = "查找品牌")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public RtnResult find(@ModelAttribute Page page) {
		return brandService.find(page);
	}

	@ApiOperation(value = "添加品牌")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public RtnResult save(
			@ApiParam(value = "name", required = true) @RequestParam(value = "name", required = true) String name,
			@ApiParam(value = "logo地址", required = true) @RequestParam(value = "logoUrl", required = true) String logoUrl,
			@ApiParam(value = "品牌类型id", required = true) @RequestParam(value = "brandTypeId", required = true) Integer brandTypeId,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = true) @RequestParam(value = "status", required = false) Integer status
			) {
		Brand brand = new Brand();
		brand.setStatus(status);
		brand.setName(name);
		brand.setLogoUrl(logoUrl);
		brand.setBrandTypeId(brandTypeId);
		return brandService.add(brand);
	}
	
	@ApiOperation(value = "更新品牌")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public RtnResult update(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "name", required = false) @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "logo地址", required = false) @RequestParam(value = "logoUrl", required = false) String logoUrl,
			@ApiParam(value = "品牌类型id", required = false) @RequestParam(value = "brandTypeId", required = false) Integer brandTypeId,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = false) @RequestParam(value = "status", required = false) Integer status) {
		Brand brand = new Brand();
		brand.setId(id);
		brand.setName(name);
		brand.setLogoUrl(logoUrl);
		brand.setBrandTypeId(brandTypeId);
		brand.setStatus(status);
		return brandService.update(brand);
	}

	
	@ApiOperation(value = "更新品牌(正常或停用)")
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public RtnResult updateStatus(
			@ApiParam(value = "id", required = true) @RequestParam(value = "id", required = true) Integer id,
			@ApiParam(value = "状态(0:正常,1:禁用)", required = true) @RequestParam(value = "status", required = true) Integer status
			) {
		return brandService.updateStatus(id, status);
	}
}
