package com.vincent.demo.controller.vo;

import com.vincent.demo.entity.SysResource;



public class SysResourceVo {
	

	private String id;

	private String url;

	private String authority;
	
	public SysResourceVo(){
		
	}
	
	public SysResourceVo(SysResource resource){
		this.setId(resource.getId());
		this.setAuthority(resource.getAuthority());
		this.setUrl(resource.getUrl());
	}

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
