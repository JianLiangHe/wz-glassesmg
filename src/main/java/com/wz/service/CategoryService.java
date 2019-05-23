package com.wz.service;

import com.wz.pojo.Category;
import com.wz.pojo.Page;
import com.wz.util.RtnResult;

public interface CategoryService {
	
	 RtnResult find(Page page);
	 
	 RtnResult add(Category category);
	 
	 RtnResult delete(Integer id);
	 
	 RtnResult updateStatus(Integer id,Integer status);
	 
	 RtnResult update(Category category);

}
