package com.wz.util.enums;

import lombok.Getter;

public class CustomerEnum {

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
	
	@Getter
	public static enum ApproveStatus {
		
		未审批(0, "未审批"),
		审批通过(1, "审批通过"),
		审批不通过(2, "审批不通过");
		
		ApproveStatus(Integer value, String text) {
            this.value = value;
            this.text = text;
        }
		
		private Integer value;
		
		private String text;
		
		public static ApproveStatus getByValue(int value) {
			for (ApproveStatus t : values()) {
				if(t.getValue().intValue() == value){
					return t;
				}
			}
			return null;
		}
		
	}
	
	@Getter
	public static enum IsDelete {
		
		否(0, "否"),
		是(1, "是");
		
		IsDelete(Integer value, String text) {
            this.value = value;
            this.text = text;
        }
		
		private Integer value;
		
		private String text;
		
		public static IsDelete getByValue(int value) {
			for (IsDelete t : values()) {
				if(t.getValue().intValue() == value){
					return t;
				}
			}
			return null;
		}
		
	}
	
}
