package com.vincent.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vincent.demo.dao.UserDao;
import com.vincent.demo.entity.User;
import com.vincent.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	@Override
	public User createUser(User u) throws Exception {
		userDao.addUser(u);
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User findByName(String name) {
		
		return userDao.findByName(name);
	}
	
	@Override
	public List<User> listAll(){
		return userDao.findAll();
	}

}
