package com.vincent.demo.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vincent.demo.dao.DocumentDao;
import com.vincent.demo.entity.Document;


@Repository
public class DocumentDaoImpl extends BaseDaoImpl<Document> implements DocumentDao {

 @Resource
 protected SessionFactory sessionFactory; 
 
 public Session getSession(){
	 return sessionFactory.getCurrentSession();
 }


}
