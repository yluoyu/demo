package com.vincent.demo.dao;

import java.util.List;

import com.vincent.demo.entity.SysResource;

public interface SysResourceDao extends BaseDao<SysResource>{

   List<SysResource> query(String url,String authority);
   
   SysResource findById(String id);
   
   SysResource saveOne(SysResource sysr);
   
   SysResource deleteOne(String id);
}
