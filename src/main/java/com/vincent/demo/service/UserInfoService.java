package com.vincent.demo.service;

import com.vincent.demo.entity.UserInfo;



public interface UserInfoService {


	void save(UserInfo userInfo);
	
    UserInfo findById(String id);
}
