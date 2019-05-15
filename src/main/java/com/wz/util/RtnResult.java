package com.wz.util;

import java.io.Serializable;

public class RtnResult implements Serializable{

	private static final long serialVersionUID = 2906675648221342609L;
	
	private Boolean result;// 结果
	private int code; // 状态码
	private String message;// 错误消息
	private Object data;// 返回对象
		
	public RtnResult() {
		this(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE);
	}
	
	public RtnResult(Object data) {
		this(WebUtils.SUCCESS_RESULT, WebUtils.SUCCESS_STATUS, WebUtils.SUCCESS_MESSAGE, data);
	}
	
	public RtnResult(Boolean result, int code) {
		this.result = result;
		this.code = code;
	}

	public RtnResult(Boolean result, int code, String message) {
		this.result = result;
		this.code = code;
		this.message = message;
	}

	public RtnResult(Boolean result, int code, String message, Object data) {
		this.result = result;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
