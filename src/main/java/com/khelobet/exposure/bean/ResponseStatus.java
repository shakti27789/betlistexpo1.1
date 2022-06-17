package com.khelobet.exposure.bean;

public class ResponseStatus {


	private String code;
	private String message;
	
	public ResponseStatus(){}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseStatus [code=" + code + ", message=" + message + "]";
	}


}
