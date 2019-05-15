package com.wz.util.enums;

public enum MobileVerifyCodeTypeEnum {

	注册(0, "register"),
	登陆(1, "login");
	
	private Integer value;
	
	private String text;
	
	MobileVerifyCodeTypeEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static MobileVerifyCodeTypeEnum getByCode(String code) {
        for (MobileVerifyCodeTypeEnum obj : MobileVerifyCodeTypeEnum.values()) {
            if (obj.getValue().equals(code)) {
                return obj;
            }
        }
		return null;
    }
	
}
