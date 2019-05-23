package com.wz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wz.dao.ProductMapper;
import com.wz.dto.FindProductDto;
import com.wz.pojo.Product;
import com.wz.pojo.ProductDetail;
import com.wz.service.ProductService;
import com.wz.service.UserService;
import com.wz.util.RtnResult;
import com.wz.util.WebUtils;
import com.wz.util.enums.ProductEnum;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public RtnResult find(FindProductDto dto) {

		Integer isDelete = ProductEnum.isDelete.否.getValue();
		dto.setIsDelete(isDelete);

		List<ProductDetail> resultList = productMapper.find(dto);
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, resultList);
	}

	@Override
	public RtnResult add(Product product) {

		Integer userId = userService.getCurrentUserId();
		product.setUserId(userId);

		try {
			productMapper.insertSelective(product);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "添加商品失败！");
		}
	}

	@Override
	public RtnResult delete(Long id) {
		
		Product p = productMapper.selectByPrimaryKey(id);

		if (p == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "找不到该商品！");
		}
		
		Integer isDelete = ProductEnum.isDelete.是.getValue();
		
		try {
			productMapper.updateIsDelete(id, isDelete);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		}catch(Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "删除商品失败！");
		}
		
	}

	@Override
	public RtnResult update(Product product) {

		Long id = product.getId();
		Product p = productMapper.selectByPrimaryKey(id);

		if (p == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "找不到该商品！");
		}

		Integer userId = userService.getCurrentUserId();
		product.setUserId(userId);

		try {
			productMapper.updateByPrimaryKeySelective(product);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新商品失败！");
		}
	}

}
