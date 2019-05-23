package com.wz.dao;

import java.util.List;

import com.wz.pojo.Brand;
import com.wz.pojo.Category;
import com.wz.pojo.Page;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
    
    //分页查询
    List<Brand> find(Page page);
    
    //更新状态
    int updateStatus(Integer id, Integer status);
    
    //通过用户名查找
    Brand getByName(String name);
    
}