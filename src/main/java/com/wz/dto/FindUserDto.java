package com.wz.dto;


import com.wz.pojo.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查找用户dto
 * @author Administrator
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindUserDto {
	
	private Integer id;
	
	private String username;
	
	private Integer status;
	
	private Page page;

}
