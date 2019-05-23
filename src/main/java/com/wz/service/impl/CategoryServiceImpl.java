package com.wz.service.impl;

import java.util.List;

import org.apache.commons.collections.list.LazyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;

import com.wz.dao.CategoryMapper;
import com.wz.pojo.Category;
import com.wz.pojo.Page;
import com.wz.service.CategoryService;
import com.wz.util.RtnResult;
import com.wz.util.WebUtils;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public RtnResult find(Page page) {

		List<Category> resultList = categoryMapper.find(page);
		return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, resultList);
	}

	@Override
	public RtnResult add(Category category) {

		String name = category.getName();
		Category c = categoryMapper.getByName(name);

		if (c != null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该种类的名字已存在！");
		}
		try {
			categoryMapper.insert(category);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "插入失败！");
		}
	}

	@Override
	public RtnResult delete(Integer id) {
		Category category = categoryMapper.selectByPrimaryKey(id);
		if (category == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该种类！");
		}
		try {
			categoryMapper.deleteByPrimaryKey(id);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "删除失败！");
		}
	}

	@Override
	public RtnResult update(Category category) {

		Integer id = category.getId();
		Category c = categoryMapper.selectByPrimaryKey(id);

		if (c == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该种类！");
		}

		String name = category.getName();
		Category cc = categoryMapper.getByName(name);

		if (cc != null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "该种类的名字已存在！");
		}

		try {
			categoryMapper.updateByPrimaryKeySelective(category);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新失败！");
		}
	}

	@Override
	public RtnResult updateStatus(Integer id, Integer status) {

		Category category = categoryMapper.selectByPrimaryKey(id);
		if (category == null) {
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "没有找到该种类！");
		}
		try {
			categoryMapper.updateStatus(id, status);
			return new RtnResult(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			return new RtnResult(WebUtils.ERROR_RESULT, WebUtils.ERROR_STATUS, "更新失败！");
		}

	}

}
