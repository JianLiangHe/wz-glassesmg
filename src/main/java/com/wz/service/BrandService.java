package com.wz.service;

import com.wz.pojo.Brand;
import com.wz.pojo.Page;
import com.wz.util.RtnResult;

public interface BrandService {
	
 RtnResult find(Page page);
	 
	 RtnResult add(Brand brand);
	 
	 RtnResult delete(Integer id);
	 
	 RtnResult updateStatus(Integer id,Integer status);
	 
	 RtnResult update(Brand brand);

}
