package com.vincent.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vincent.demo.dao.SysResourceDao;
import com.vincent.demo.entity.SysResource;
import com.vincent.demo.service.SysResourceService;

@Service
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	private SysResourceDao sysResourceDao;
	
	@Override
	public List<SysResource> listAll() {
		return sysResourceDao.findAll();
		
	}

}
