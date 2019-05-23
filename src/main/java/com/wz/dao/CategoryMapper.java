package com.wz.dao;



import java.util.List;

import com.wz.pojo.Category;
import com.wz.pojo.Page;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    //分页查询
    List<Category> find(Page page);
    
    //更新状态
    int updateStatus(Integer id, Integer status);
    
    //通过用户名查找
    Category getByName(String name);
    
}