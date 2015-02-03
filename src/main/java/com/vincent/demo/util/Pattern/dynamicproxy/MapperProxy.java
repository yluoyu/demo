package com.vincent.demo.util.Pattern.dynamicproxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;


public class MapperProxy <T> implements InvocationHandler, Serializable{
	
	  private static final long serialVersionUID = -6424540398559729838L;
	  private final SqlSession sqlSession;
	  private final Class<T> mapperInterface;
	public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
	    this.sqlSession = sqlSession;
	    this.mapperInterface = mapperInterface;
	  }

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return new MapperMethod().execute(sqlSession, args);
	}

}
