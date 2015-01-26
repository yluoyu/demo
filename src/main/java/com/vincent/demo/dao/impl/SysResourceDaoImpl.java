package com.vincent.demo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vincent.demo.dao.SysResourceDao;
import com.vincent.demo.entity.SysResource;

@Repository
public class SysResourceDaoImpl extends BaseDaoImpl<SysResource> implements SysResourceDao{

   public List<SysResource> findAll(){
	   Query query =  this.getSession().createQuery("from SysResource s");
	   List<SysResource> list = query.list();
	   return list;
   }

}
