package com.vincent.demo.util.Pattern.dynamicproxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MapperProxyFactory<T> {

	private final Class<T> mapperInterface;
	
	 private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();
	
	  public MapperProxyFactory(Class<T> mapperInterface) {
		    this.mapperInterface = mapperInterface;
		  }
	
	  
	  @SuppressWarnings("unchecked")
	  protected T newInstance(MapperProxy<T> mapperProxy) {
	    return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
	  }
	
	public  T newInstance(SqlSession sqlSession){
		 final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession,  mapperInterface, methodCache);
		 return newInstance(mapperProxy);
	}
}
