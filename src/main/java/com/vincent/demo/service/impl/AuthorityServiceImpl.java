package com.vincent.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vincent.demo.dao.AuthorityDao;
import com.vincent.demo.entity.Authority;
import com.vincent.demo.service.AuthorityService;

@Service
public class AuthorityServiceImpl  implements AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;
	
	public List<Authority> listAll(){
		
		
		return authorityDao.findAll();
	}
}
