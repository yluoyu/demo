package com.vincent.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_AUTHORITY") 
public class Authority {
	@Id
	@Column(name = "AUTHORITY_ID", unique = true, nullable = false, length = 4)
	private String id;

	@Column(name = "AUTHORITY_NAME", nullable = true, length = 16)
	private String name;

	@Column(name = "AUTHORITY_TITLE", nullable = true, length = 16)
	private String title;

	@Column(name = "DESCRIPTION", nullable = true, length = 64)
	private String description;

	@ManyToMany(fetch=FetchType.LAZY, mappedBy="authorityies")
	private Set<Role> roles;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
