package com.vincent.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_RESOURCE")
public class SysResource {
	
	@Id
	@Column(name = "RESOURCE_ID", unique = true, nullable = false, length = 8)
	private String id;

	@Column(name = "URL", nullable = true, length = 128)
	private String url;

	@Column(name = "AUTHORITY", nullable = true, length = 16)
	private String authority;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
