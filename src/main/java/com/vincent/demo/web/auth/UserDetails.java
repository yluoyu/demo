package com.vincent.demo.web.auth;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.vincent.common.StringUtil;
import com.vincent.common.log.Logger;
import com.vincent.demo.entity.Authority;
import com.vincent.demo.entity.Role;
import com.vincent.demo.entity.User;
import com.vincent.demo.service.UserService;
import com.vincent.demo.util.Constants;
import com.vincent.demo.util.ServerUtil;

@SuppressWarnings("serial")
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

private static final Logger log = new Logger(UserDetails.class);
	
	private User user;
	
	private Map<String, Collection<GrantedAuthority>> privilageMap = new HashMap<String, Collection<GrantedAuthority>>();
	
	public UserDetails(User user){
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		log.debug("Invoke Method - getAuthorities");
		Collection<GrantedAuthority> grantedAuthorities = this.privilageMap.get(user.getId());
		if (grantedAuthorities != null) {
			log.debug("Get permissions in cache");
			return grantedAuthorities;
		}
		
		grantedAuthorities = new HashSet<GrantedAuthority>(); 
		UserService userService = ServerUtil.getBean(UserService.class);
/*		Set<Permission> userModules = userService.getUserModules(this.user.getId());
		for (Permission m : userModules) {
			grantedAuthorities.add(new SimpleGrantedAuthority(StringUtil.nullToEmpty(m.getName())));
		}*/
		Set<Role> roles =   userService.findByName(getUsername()).getRoles();
		for(Role role : roles){
			Set<Authority> authorities = role.getAuthorityies();
			for(Authority authority : authorities){
				grantedAuthorities.add(new SimpleGrantedAuthority(StringUtil.nullToEmpty(authority.getName())));
			}
		}
		this.privilageMap.put(this.user.getId(), grantedAuthorities);
		log.debug(grantedAuthorities.size() + " GrantedAuthority found");
        return grantedAuthorities;  
	}

	@Override
	public String getPassword() {
		String password = null;
		if (this.user != null) {
			password = this.user.getPassword();
		}
		return password;
	}

	@Override
	public String getUsername() {
		String username = null;
		if (this.user != null) {
			username = this.user.getUsername();
		}
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		boolean enabled = false;
		if (this.user != null) {
			if (user.getStatus() == Constants.USER_STATUS_ACTIVE || user.getStatus() == Constants.USER_STATUS_SYSTEM) {
				enabled = true;
			}
		}
		return enabled;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.user.getId()==null ? 0 : this.user.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetails other = (UserDetails) obj;
		if (this.user.getId()==null) {
			return false;
		} else if (other.getUserid()==null || !this.user.getId().equals(other.getUserid())) {
			return false;
		}
		return true;
	}
	
	public User getCachedUser(){
		return this.user;
	}
	
	public String getUserid(){
		String id = null;
		if (this.user != null) {
			id = this.user.getId();
		}
		return id;
	}
	
	public String getType(){
		String type = null;
		if (this.user != null) {
			type = this.user.getType();
		}
		return type;
	}

}
