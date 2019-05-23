package com.wz.service;

import com.wz.dto.FindProductDto;
import com.wz.pojo.Product;
import com.wz.util.RtnResult;

public interface ProductService {
	
	RtnResult find(FindProductDto dto);
	
	RtnResult add(Product product);
	
	RtnResult delete(Long id);
	
	RtnResult update(Product product);
	

}
