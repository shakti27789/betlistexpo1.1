package com.khelobet.exposure.bean;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMapping<T> {


	private ResponseStatus responseStatus;

	private Boolean isCreatedBetList;
	
	private T responseObj;

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Boolean getIsCreatedBetList() {
		return isCreatedBetList;
	}

	public void setIsCreatedBetList(Boolean isCreatedBetList) {
		this.isCreatedBetList = isCreatedBetList;
	}

	
	
	public T getResponseObj() {
		return responseObj;
	}

	public void setResponseObj(T responseObj) {
		this.responseObj = responseObj;
	}

	@Override
	public String toString() {
		return "ResponseMapping [responseStatus=" + responseStatus + ", isCreatedBetList=" + isCreatedBetList + "]";
	}


}
