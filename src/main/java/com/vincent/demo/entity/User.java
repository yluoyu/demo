package com.vincent.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name = "T_USER") 
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "paymentableGenerator", strategy = "native")
	@Column(name = "USER_ID", unique = true, nullable = false, length = 16)
	private String id;

	@Column(name = "USERNAME", nullable = true, length = 16)
	private String username;

	@Column(name = "PASSWORD", nullable = true, length = 64)
	private String password;

	@Column(name = "TYPE", length = 16)
	private String type;

	@Column(name = "STATUS")
	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = true, length = 7)
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME", nullable = true, length = 7)
	private Date updateTime;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "T_USER_ROLE",
		joinColumns = {
			@JoinColumn(name = "USER_ID", nullable = false, updatable = false)
		}, 
		inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)
		}
	)
	private Set<Role> roles;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


}
