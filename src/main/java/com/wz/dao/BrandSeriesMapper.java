package com.wz.dao;

import java.util.List;

import com.wz.pojo.Brand;
import com.wz.pojo.BrandSeries;
import com.wz.pojo.Page;

public interface BrandSeriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandSeries record);

    int insertSelective(BrandSeries record);

    BrandSeries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandSeries record);

    int updateByPrimaryKey(BrandSeries record);
    
  //分页查询
    List<Brand> find(Page page);
    
    //更新状态
    int updateStatus(Integer id, Integer status);
    
    //通过用户名查找
    BrandSeries getByName(String name);
}