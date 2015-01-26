package com.vincent.demo.web.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class Token extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// TODO: additional details for session
	private Object session; 
	
	private String type;
	
	private List<String> groups = new ArrayList<String>();
	
	public Token(Object principal, Object credentials) {
		super(principal, credentials, new ArrayList<GrantedAuthority>());
	}

	public Object getSession() {
		return session;
	}

	public void setSession(Object session) {
		this.session = session;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getGroups() {
		return groups;
	}

	public void setGroups(List<String> groups) {
		this.groups.clear();
		this.groups.addAll(groups);
	}

}
