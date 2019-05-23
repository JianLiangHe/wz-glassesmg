package com.wz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wz.dao.BrandSeriesMapper;
import com.wz.pojo.Brand;
import com.wz.pojo.BrandSeries;
import com.wz.pojo.Page;
import com.wz.service.BrandSeriesService;
import com.wz.util.RtnResult;
import com.wz.util.WebUtils;

@Service("brandSeriesService")
public class BrandSeriesServiceImpl implements BrandSeriesService {
	
	@Autowired
	private BrandSeriesMapper brandSeriesMapper;

	@Override
	public RtnResult find(Page page) {

		List<Brand> resultList = brandSeriesMapper.find(page);
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, resultList);
	}

	@Override
	public RtnResult add(BrandSeries brandSeries) {

		String name = brandSeries.getName();
		BrandSeries b = brandSeriesMapper.getByName(name);

		if (b != null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该品牌的名字已存在！");
		}
		try {
			brandSeriesMapper.insert(brandSeries);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "插入失败！");
		}
	}

	@Override
	public RtnResult delete(Integer id) {
		BrandSeries brandSeries = brandSeriesMapper.selectByPrimaryKey(id);
		if (brandSeries == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}
		try {
			brandSeriesMapper.deleteByPrimaryKey(id);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "删除失败！");
		}
	}

	@Override
	public RtnResult update(BrandSeries brandSeries) {

		Integer id = brandSeries.getId();
		BrandSeries b = brandSeriesMapper.selectByPrimaryKey(id);

		if (b == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}

		String name = brandSeries.getName();
		BrandSeries bb = brandSeriesMapper.getByName(name);

		if (bb != null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该品牌的名字已存在！");
		}

		try {
			brandSeriesMapper.updateByPrimaryKeySelective(brandSeries);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新失败！");
		}
	}

	@Override
	public RtnResult updateStatus(Integer id, Integer status) {

		BrandSeries brandSeries = brandSeriesMapper.selectByPrimaryKey(id);
		if (brandSeries == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该品牌！");
		}
		try {
			brandSeriesMapper.updateStatus(id, status);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新失败！");
		}
	
	}
}
