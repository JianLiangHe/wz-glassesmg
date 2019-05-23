package com.wz.util.enums;

import com.wz.util.enums.StatusEnum.Status;

import lombok.Getter;

public class ProductEnum {
	
	@Getter
	public static enum isDelete {
		
		否(0, "false"),
		是(1, "true");
		
		isDelete(Integer value, String text) {
            this.value = value;
            this.text = text;
        }
		
		private Integer value;
		
		private String text;
		
		public static isDelete getByValue(int value) {
			for (isDelete t : values()) {
				if(t.getValue().intValue() == value){
					return t;
				}
			}
			return null;
		}
		
	}
	
	@Getter
	public static enum Status {
		
		上架(0, "上架"),
		下架(1, "下架");
		
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
