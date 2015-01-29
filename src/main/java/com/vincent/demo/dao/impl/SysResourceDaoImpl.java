package com.vincent.demo.dao.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.vincent.demo.dao.SysResourceDao;
import com.vincent.demo.entity.SysResource;

@Repository
public class SysResourceDaoImpl extends BaseDaoImpl<SysResource> implements SysResourceDao{

	public SqlSession getSqlSession(){
		SqlSession sql = null;
		DBAccess db = new DBAccess();
		try {
		   sql = db.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sql;
	}
	
   public List<SysResource> findAll(SysResource sysr){
	   
/*	   Query query =  this.getSession().createQuery("from SysResource s");
	   List<SysResource> list = query.list();*/
	   SqlSession sqlSession = this.getSqlSession();  
	   List<SysResource> list = sqlSession.selectList("Resource.queryResourceList", sysr);
	   return list;
   }

@Override
public List<SysResource> query(String url, String authority) {
	 SysResource sysr = new SysResource();
	 sysr.setAuthority(authority);
	 sysr.setUrl(url);
	   SqlSession sqlSession = this.getSqlSession();  
	   List<SysResource> list = sqlSession.selectList("Resource.queryResourceList", sysr);
	   return list;
}

@Override
public SysResource findById(String id) {
	 SqlSession sqlSession = this.getSqlSession();  
	 SysResource sysResource= sqlSession.selectOne("Resource.findById", id);
	return sysResource;
}

@Override
public SysResource saveOne(SysResource sysr) {
	 SqlSession sqlSession = this.getSqlSession(); 
	 sqlSession.insert("Resource.add", sysr);
	 sqlSession.commit();
	return null;
}

@Override
public SysResource deleteOne(String id) {
	 SqlSession sqlSession = this.getSqlSession(); 
	 sqlSession.insert("Resource.delete", id);
	 sqlSession.commit();
	return null;
}
   

}
