package com.vincent.demo.dao;

import java.util.List;

public interface BaseDao<T> {
	
	public void save(T entity);
	
	public void delete(T entity);
	
	public void delete(String id);
	
	public List<T> findAll();
	
	public T get(final String id);
	
	//public List find(String hql,Object... values);

}
