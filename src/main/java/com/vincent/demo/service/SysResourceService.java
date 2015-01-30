package com.vincent.demo.service;

import java.util.List;

import com.vincent.demo.entity.SysResource;

public interface SysResourceService {
    List<SysResource> listAll(String url,String authority);
    
    SysResource findById(String id);
    
    SysResource save(SysResource sysr);
    
    SysResource delete(String id);
}
