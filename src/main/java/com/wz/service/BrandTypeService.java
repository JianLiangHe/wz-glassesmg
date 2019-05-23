package com.wz.service;

import com.wz.pojo.Brand;
import com.wz.pojo.BrandType;
import com.wz.pojo.Page;
import com.wz.util.RtnResult;

public interface BrandTypeService {
	
 RtnResult find(Page page);
	 
	 RtnResult add(BrandType brandType);
	 
	 RtnResult delete(Integer id);
	 
	 RtnResult updateStatus(Integer id,Integer status);
	 
	 RtnResult update(BrandType brandType);

}
