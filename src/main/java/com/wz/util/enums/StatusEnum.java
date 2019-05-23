package com.wz.util.enums;

import com.wz.util.enums.CustomerEnum.Status;

import lombok.Getter;

public class StatusEnum {
	
	@Getter
	public static enum Status {
		
		正常(0, "正常"),
		禁用(1, "禁用");
		
		Status(Integer value, String text) {
            this.value = value;
            this.text = text;
        }
		
		private Integer value;
		
		private String text;
		
		public static Status getByValue(int value) {
			for (Status t : values()) {
				if(t.getValue().intValue() == value){
					return t;
				}
			}
			return null;
		}
		
	}
	

}
