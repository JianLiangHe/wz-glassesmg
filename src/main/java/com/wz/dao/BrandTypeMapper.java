package com.wz.dao;

import java.util.List;

import com.wz.pojo.Brand;
import com.wz.pojo.BrandType;
import com.wz.pojo.Page;

public interface BrandTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BrandType record);

    int insertSelective(BrandType record);

    BrandType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrandType record);

    int updateByPrimaryKey(BrandType record);
    
    //分页查询
    List<BrandType> find(Page page);
    
    //更新状态
    int updateStatus(Integer id, Integer status);
    
    //通过用户名查找
    BrandType getByName(String name);
}