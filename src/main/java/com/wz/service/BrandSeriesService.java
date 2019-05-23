package com.wz.service;

import com.wz.pojo.BrandSeries;
import com.wz.pojo.Page;
import com.wz.util.RtnResult;

public interface BrandSeriesService {
	
 RtnResult find(Page page);
	 
	 RtnResult add(BrandSeries brandSeries);
	 
	 RtnResult delete(Integer id);
	 
	 RtnResult updateStatus(Integer id,Integer status);
	 
	 RtnResult update(BrandSeries brandSeries);

}
