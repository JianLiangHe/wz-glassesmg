package com.wz.dto;

import com.wz.pojo.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindProductDto {
	
	private String name;
	
	private Integer status;
	
	//品牌系列id
	private Integer brandSeriesId;
	
	private Page page;
	
	private Integer isDelete;

}
