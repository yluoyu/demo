package com.vincent.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vincent.demo.dao.UserInfoDao;
import com.vincent.demo.entity.UserInfo;
import com.vincent.demo.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Override
	public void save(UserInfo userInfo) {
		userInfoDao.save(userInfo);
	}

	@Override
	public UserInfo findById(String id) {
		return userInfoDao.get(id);
	}
	


}
