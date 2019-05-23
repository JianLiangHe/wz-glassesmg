package com.wz.dto;

import com.wz.pojo.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindCustomerDto {
	
	private Integer id;
	
	private Integer status;
	
	private Integer approveStatus;
	
	private String shopName;
	
	private String city;
	
	private String mobile;
	
	private Integer isDelete;
	
	private Page page;
	
	

}
