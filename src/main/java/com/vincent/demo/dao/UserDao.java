package com.vincent.demo.dao;

import com.vincent.demo.entity.User;



public interface UserDao extends BaseDao<User> {
  public void addUser(User u) throws Exception;
  public User findByName(String userName);

}
