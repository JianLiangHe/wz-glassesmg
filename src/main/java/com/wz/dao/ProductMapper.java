package com.wz.dao;

import java.util.List;

import com.wz.dto.FindProductDto;
import com.wz.pojo.Product;
import com.wz.pojo.ProductDetail;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);
    
    List <ProductDetail> find(FindProductDto dto);
    
    int updateIsDelete(Long id,Integer isDelete);
}