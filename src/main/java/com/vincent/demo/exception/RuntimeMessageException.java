package com.vincent.demo.exception;

import com.vincent.demo.util.ServerUtil;


@SuppressWarnings("serial")
public class RuntimeMessageException extends RuntimeException implements IMessageException {

	public RuntimeMessageException(String code, Object... args){
		super(ServerUtil.getMessage(code, args));
	}
	
}
