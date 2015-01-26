package com.vincent.demo.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vincent.demo.dao.UserInfoDao;
import com.vincent.demo.entity.UserInfo;


@Repository
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao {

 @Resource
 protected SessionFactory sessionFactory; 
 
 public Session getSession(){
	 return sessionFactory.getCurrentSession();
 }

@Override
public void addUser(UserInfo u) throws Exception {
	Session session = this.getSession();
	session.save(u);
	session.flush();
}

}
