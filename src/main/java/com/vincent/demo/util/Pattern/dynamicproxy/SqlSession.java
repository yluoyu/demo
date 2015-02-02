package com.vincent.demo.util.Pattern.dynamicproxy;

public class SqlSession {

	public <T> T getMapper(Class<T> type){
        MapperProxyFactory<T> mapperProxyFactory = new MapperProxyFactory<T>(type);
        return (T)mapperProxyFactory.newInstance(this);
	}
	
}
