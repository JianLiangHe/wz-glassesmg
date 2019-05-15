package com.wz.dao;

import com.wz.pojo.BrandSeries;

public interface BrandSeriesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandSeries record);

    int insertSelective(BrandSeries record);

    BrandSeries selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandSeries record);

    int updateByPrimaryKey(BrandSeries record);
}