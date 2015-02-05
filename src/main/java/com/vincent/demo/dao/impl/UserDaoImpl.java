package com.vincent.demo.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.vincent.demo.dao.UserDao;
import com.vincent.demo.entity.User;


@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

 @Resource
 protected SessionFactory sessionFactory; 
 
 public Session getSession(){
	 return sessionFactory.getCurrentSession();
 }

@Override
public void addUser(User u) throws Exception {
	Session session = this.getSession();
	session.save(u);
	session.flush();
}

@Override
public User findByName(String userName) {
	Session session = this.getSession();
	Query query = session
		    .createQuery("from User u where u.username=?");
	query.setParameter(0, userName);
	List<User> list = query.list();   
    for(User users : list){   
        System.out.println(users.getId());
        users.getRoles();
    }   
    return list.get(0);
}
}
