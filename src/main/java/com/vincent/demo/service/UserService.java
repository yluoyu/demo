package com.vincent.demo.service;

import java.util.List;
import java.util.Set;



import com.vincent.demo.entity.User;



public interface UserService {


	User createUser(User u) throws Exception;
	
    User findByName(String name);
	
    void save(User u);
    public List<User> listAll();
}
