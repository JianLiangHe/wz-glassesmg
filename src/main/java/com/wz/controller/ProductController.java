package com.wz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wz.dto.FindProductDto;
import com.wz.pojo.Page;
import com.wz.pojo.Product;
import com.wz.service.ProductService;
import com.wz.util.RtnResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "商品接口")
@RestController
@RequestMapping("/api/product/")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "查找商品")
	@RequestMapping(value = "find", method = RequestMethod.POST)
	public RtnResult find(
			@ModelAttribute Page page,
			@ApiParam(value = "name", required = false) @RequestParam(value = "username", required = false) String name,
			@ApiParam(value = "品牌id", required = false) @RequestParam(value = "brandSeriesId", required = false) Integer brandSeriesId,
			@ApiParam(value = "状态(0:上架,1:下架)", required = false) @RequestParam(value = "status", required = false) Integer status
			)
	 {
		FindProductDto dto = new FindProductDto();
		dto.setPage(page);
		dto.setName(name);
		dto.setStatus(status);
		dto.setBrandSeriesId(brandSeriesId);
		return productService.find(dto);
	}
	
	@ApiOperation(value = "添加商品")
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public RtnResult add(
			@ApiParam(value = "name", required = false) @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "路片路径", required = false) @RequestParam(value = "picUrl", required = false) String picUrl,
			@ApiParam(value = "原价（分）", required = false) @RequestParam(value = "originalPrice", required = false) Integer originalPrice ,
			@ApiParam(value = "当前价(分)", required = false) @RequestParam(value = "currentPrice", required = false) Integer currentPrice,
			@ApiParam(value = "单位", required = false) @RequestParam(value = "unit", required = false) String unit,
			@ApiParam(value = "商品详情", required = false) @RequestParam(value = "info", required = false) String info,
			@ApiParam(value = "状态(0:上架,1:下架)", required = false) @RequestParam(value = "status", required = false) Integer status,
			@ApiParam(value = "品牌id", required = false) @RequestParam(value = "brandSeriesId", required = false) Integer brandSeriesId,
			@ApiParam(value = "产品规格json", required = false) @RequestParam(value = "specificationJson", required = false) String specificationJson,
			@ApiParam(value = "guid", required = false) @RequestParam(value = "guid", required = false) String guid
			)
	 {
		Product product = new Product();
		product.setName(name);
		product.setGuid(guid);
		product.setInfo(info);
		product.setOriginalPrice(originalPrice);
		product.setCurrentPrice(currentPrice);
		product.setPicUrl(picUrl);
		product.setSpecificationJson(specificationJson);
		product.setUnit(unit);
		product.setBrandSeriesId(brandSeriesId);
		product.setStatus(status);
		return productService.add(product);	
	}
	
	@ApiOperation(value = "更新商品")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public RtnResult update(
			@ApiParam(value = "id", required = false) @RequestParam(value = "id", required = false) Long id,
			@ApiParam(value = "name", required = false) @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "路片路径", required = false) @RequestParam(value = "picUrl", required = false) String picUrl,
			@ApiParam(value = "原价（分）", required = false) @RequestParam(value = "originalPrice", required = false) Integer originalPrice ,
			@ApiParam(value = "当前价(分)", required = false) @RequestParam(value = "currentPrice", required = false) Integer currentPrice,
			@ApiParam(value = "单位", required = false) @RequestParam(value = "unit", required = false) String unit,
			@ApiParam(value = "商品详情", required = false) @RequestParam(value = "info", required = false) String info,
			@ApiParam(value = "状态(0:上架,1:下架)", required = false) @RequestParam(value = "status", required = false) Integer status,
			@ApiParam(value = "品牌id", required = false) @RequestParam(value = "brandSeriesId", required = false) Integer brandSeriesId,
			@ApiParam(value = "产品规格json", required = false) @RequestParam(value = "specificationJson", required = false) String specificationJson,
			@ApiParam(value = "guid", required = false) @RequestParam(value = "guid", required = false) String guid
			)
	 {
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setGuid(guid);
		product.setInfo(info);
		product.setOriginalPrice(originalPrice);
		product.setCurrentPrice(currentPrice);
		product.setPicUrl(picUrl);
		product.setSpecificationJson(specificationJson);
		product.setUnit(unit);
		product.setBrandSeriesId(brandSeriesId);
		product.setStatus(status);
		return productService.update(product);
	}
	
	@ApiOperation(value = "删除商品")
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public RtnResult delete(
			@ApiParam(value = "id", required = false) @RequestParam(value = "id", required = false) Long id)
	 {
		return productService.delete(id);
	}


}
