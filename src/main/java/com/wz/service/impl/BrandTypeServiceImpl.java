package com.wz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wz.dao.BrandTypeMapper;
import com.wz.pojo.Brand;
import com.wz.pojo.BrandType;
import com.wz.pojo.Page;
import com.wz.service.BrandTypeService;
import com.wz.util.RtnResult;
import com.wz.util.WebUtils;

@Service("brandTypeService")
public class BrandTypeServiceImpl implements BrandTypeService {

	
	@Autowired
	private BrandTypeMapper brandTypemapper;
	
	@Override
	public RtnResult find(Page page) {

		List<BrandType> resultList = brandTypemapper.find(page);
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, resultList);
	}

	@Override
	public RtnResult add(BrandType brandType) {

		String name = brandType.getName();
		BrandType b = brandTypemapper.getByName(name);

		if (b != null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该品牌的名字已存在！");
		}
		try {
			brandTypemapper.insert(brandType);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "插入失败！");
		}
	}

	@Override
	public RtnResult delete(Integer id) {
		BrandType brandType = brandTypemapper.selectByPrimaryKey(id);
		if (brandType == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}
		try {
			brandTypemapper.deleteByPrimaryKey(id);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "删除失败！");
		}
	}

	@Override
	public RtnResult update(BrandType brandType) {

		Integer id = brandType.getId();
		BrandType b = brandTypemapper.selectByPrimaryKey(id);

		if (b == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}

		String name = brandType.getName();
		BrandType bb = brandTypemapper.getByName(name);

		if (bb != null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该品牌的名字已存在！");
		}

		try {
			brandTypemapper.updateByPrimaryKeySelective(brandType);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新失败！");
		}
	}

	@Override
	public RtnResult updateStatus(Integer id, Integer status) {

		BrandType brandType = brandTypemapper.selectByPrimaryKey(id);
		if (brandType == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}
		try {
			brandTypemapper.updateStatus(id, status);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新失败！");
		}

	}

}
