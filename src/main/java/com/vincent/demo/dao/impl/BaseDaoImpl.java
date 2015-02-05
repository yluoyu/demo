package com.vincent.demo.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.vincent.demo.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T>{
	 @Resource
	protected SessionFactory sessionFactory;
	protected Class<?> entityClass;
	
	 public BaseDaoImpl() {
	        entityClass =(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	    }
    public Session getSession(){
    	return sessionFactory.getCurrentSession();
    }
	@Override
	public void save(T entity) {
		this.getSession().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getSession().delete(entity);
		
	}

	@Override
	public void delete(String id) {
		T entiry  = this.get(id);
		this.delete(entiry);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getSession().createCriteria(entityClass).list();
	}
	
	

	@Override
	public T get(String id) {
		
		return (T)this.getSession().get(this.entityClass, id);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
