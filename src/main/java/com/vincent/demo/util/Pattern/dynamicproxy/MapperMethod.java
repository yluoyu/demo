package com.vincent.demo.util.Pattern.dynamicproxy;


public class MapperMethod {
	public Object execute(SqlSession sqlSession, Object[] args) {
		System.out.println("do query");
		UserInfo userInfo = new UserInfo();
		userInfo.setId("1");
		userInfo.setUsername("vincent");
		return userInfo;
	}
}
