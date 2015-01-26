package com.vincent.demo.util;

public class Constants {
	
	public static final Integer AJAX_RESPONSE_STATUS_ERROR = 0;
	public static final Integer AJAX_RESPONSE_STATUS_SUCCESS = 1;
	
	public static final String SESSION_KEY_ERROR_LOGIN = Constants.class.getName() + ".error.login";
	public static final String SESSION_KEY_VERIFY_CODE = Constants.class.getName() + ".randomCode";

	public static final String AJAX_RESPONSE_KEY_STATUS = "status";
	public static final String AJAX_RESPONSE_KEY_MESSAGE = "message";
	public static final String AJAX_RESPONSE_KEY_RESULT = "result";
	
	public static final Integer USER_STATUS_SYSTEM = 0;
	public static final Integer USER_STATUS_NEW = 1;
	public static final Integer USER_STATUS_ACTIVE = 10;
	public static final Integer USER_STATUS_DELETED = 99;

}
