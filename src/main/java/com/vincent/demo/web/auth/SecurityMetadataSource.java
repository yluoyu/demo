package com.vincent.demo.web.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.vincent.common.StringUtil;
import com.vincent.common.log.Logger;
import com.vincent.demo.entity.SysResource;
import com.vincent.demo.service.SysResourceService;


public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Logger log = new Logger(SecurityMetadataSource.class);
	
	@Autowired
	private SysResourceService sysResourceService;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
	
	@PostConstruct
	public void loadResourceDefine() {
		log.debug("Load defined resources");
		List<SysResource> resources = sysResourceService.listAll(null,null);
		if (resources != null) {
			for (SysResource resource : resources) {
				String url = resource.getUrl();
				if (!StringUtil.isNullOrEmpty(url)) {
					url = url.trim();
					if (url.startsWith("/")) {
						 Collection<ConfigAttribute> configAttributes = resourceMap.get(url);
						if (configAttributes == null) {
							configAttributes = new ArrayList<ConfigAttribute>();
							resourceMap.put(url, configAttributes);
						}
						configAttributes.add(new SecurityConfig(resource.getAuthority()));
					}
				}
			}
		}
		log.debug(resourceMap.size() + " resource definition found");
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		log.debug("Get ConfigAttribute");
		String url = ((FilterInvocation)object).getRequestUrl();
		int index = url.indexOf("?");
		if (index!=-1) {
			url = url.substring(0, index);
		}
		if (url.endsWith("/")) {
			url = url.substring(0, url.length()-1);
		}
		Collection<ConfigAttribute> permissions = resourceMap.get(url);
        
        Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
        if (permissions != null) {
        	for (ConfigAttribute permission : permissions) {
        		attrs.add(permission);
        	}
        }
        log.debug(attrs.size() + " ConfigAttribute found");
        return attrs;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
