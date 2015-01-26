package com.vincent.demo.controller.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.vincent.demo.entity.User;
import com.vincent.demo.entity.UserInfo;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserVo {
	
	private String id;
	
	private String username;
	
	private Date createTime;
	
	private Date updateTime;

	private int status;
	
	private String type;
	
	public UserVo(User user){
		this.setCreateTime(user.getCreateTime());
		this.setUpdateTime(user.getUpdateTime());
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setType(user.getType());
		this.setStatus(user.getStatus());
	}
	
	public UserVo(UserInfo userInfo){
		this.setId(userInfo.getId());
		this.setCreateTime(userInfo.getCreateTime());
		this.setUsername(userInfo.getUsername());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
