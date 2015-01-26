package com.vincent.demo.web.auth;

import java.util.Collection;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import com.vincent.common.StringUtil;
import com.vincent.common.log.Logger;
import com.vincent.demo.exception.RuntimeMessageException;
import com.vincent.demo.util.ServerUtil;



public class AccessDecisionManager implements org.springframework.security.access.AccessDecisionManager {
	
	private static final Logger log = new Logger(AccessDecisionManager.class);
	
	public AccessDecisionManager() {
	}

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		log.debug("start Method - decide");
		if (configAttributes == null) {
			return;
		}
		
		String userId = ServerUtil.getUserId();
		String url = ((FilterInvocation) object).getRequestUrl();
		if(StringUtil.isNullOrEmpty(userId, false) == false){
			String type = ServerUtil.getUserType();
			if (StringUtil.isNullOrEmpty(type, false)) {
				log.warn("Access denied : no role found or role not matched for user : " + ServerUtil.getUserName());
				throw new AccessDeniedException("No permission to access", new RuntimeMessageException("no.permission"));
			}
				for (ConfigAttribute ca : configAttributes) {
					String needPermission = ca.getAttribute();
					if(StringUtil.isNullOrEmpty(needPermission, false)){
						log.debug("No permission need");
						return;
					}
					
					for (GrantedAuthority ga : authentication.getAuthorities()) {
						if (ga == null)
							continue;

						String authority = ga.getAuthority();
						if(StringUtil.isNullOrEmpty(authority, false)){
							continue;
						}
						
						if (authority.equals(needPermission)) {
							log.debug("Matched Authority found : " + authority);
							return;
						}
					}
				}
			
		}
		log.warn("Access denied : no matched Authority found for url : " + url + " from address : " + ((FilterInvocation) object).getHttpRequest().getRemoteAddr());
		throw new AccessDeniedException("No permission to access");
	}


	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
