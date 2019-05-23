package com.wz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wz.dao.BrandMapper;
import com.wz.pojo.Brand;
import com.wz.pojo.Category;
import com.wz.pojo.Page;
import com.wz.service.BrandService;
import com.wz.util.RtnResult;
import com.wz.util.WebUtils;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

	
	@Autowired
	private BrandMapper brandmapper;
	
	@Override
	public RtnResult find(Page page) {

		List<Brand> resultList = brandmapper.find(page);
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, resultList);
	}

	@Override
	public RtnResult add(Brand brand) {

		String name = brand.getName();
		Brand b = brandmapper.getByName(name);

		if (b != null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该品牌的名字已存在！");
		}
		try {
			brandmapper.insert(brand);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "插入失败！");
		}
	}

	@Override
	public RtnResult delete(Integer id) {
		Brand brand = brandmapper.selectByPrimaryKey(id);
		if (brand == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}
		try {
			brandmapper.deleteByPrimaryKey(id);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "删除失败！");
		}
	}

	@Override
	public RtnResult update(Brand brand) {

		Integer id = brand.getId();
		Brand b = brandmapper.selectByPrimaryKey(id);

		if (b == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}

		String name = brand.getName();
		Brand bb = brandmapper.getByName(name);

		if (bb != null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该品牌的名字已存在！");
		}

		try {
			brandmapper.updateByPrimaryKeySelective(brand);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新失败！");
		}
	}

	@Override
	public RtnResult updateStatus(Integer id, Integer status) {

		Brand brand = brandmapper.selectByPrimaryKey(id);
		if (brand == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}
		try {
			brandmapper.updateStatus(id, status);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新失败！");
		}

	}
}
