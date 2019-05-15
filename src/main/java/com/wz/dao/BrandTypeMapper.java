package com.wz.dao;

import com.wz.pojo.BrandType;

public interface BrandTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandType record);

    int insertSelective(BrandType record);

    BrandType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandType record);

    int updateByPrimaryKey(BrandType record);
}