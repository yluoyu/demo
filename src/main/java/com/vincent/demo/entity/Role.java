package com.vincent.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_ROLE") 
public class Role {

	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 4)
	private String id;

	@Column(name = "ROLE_NAME", nullable = true, length = 16)
	private String name;
	
	@Column(name = "ROLE_KEY", nullable = true, length = 16)
	private String key;

	@Column(name = "TYPE", nullable = true, length = 16)
	private String type;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy="roles")
	private Set<User> users;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "T_ROLE_AUTHORITY",
		joinColumns = {
			@JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)
		}, 
		inverseJoinColumns = {
			@JoinColumn(name = "AUTHORITY_ID", nullable = false, updatable = false)
		}
	)
	private Set<Authority> authorityies;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Authority> getAuthorityies() {
		return authorityies;
	}

	public void setAuthorityies(Set<Authority> authorityies) {
		this.authorityies = authorityies;
	}
}
