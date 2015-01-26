package com.vincent.demo.dao;

import com.vincent.demo.entity.User;
import com.vincent.demo.entity.UserInfo;



public interface UserInfoDao extends BaseDao<UserInfo> {
  public void addUser(UserInfo u) throws Exception;

}
