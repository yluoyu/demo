package com.vincent.demo.controller.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class JsonResponse<T> {

	private int success;
	
	private String message;
	
	private T data;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T result) {
		this.data = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}