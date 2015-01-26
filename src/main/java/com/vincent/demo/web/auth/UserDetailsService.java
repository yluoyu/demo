package com.vincent.demo.web.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import com.vincent.common.StringUtil;
import com.vincent.common.log.Logger;
import com.vincent.demo.entity.User;
import com.vincent.demo.exception.RuntimeMessageException;
import com.vincent.demo.service.UserService;
import com.vincent.demo.util.Constants;
import com.vincent.demo.util.ServerUtil;

public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private static final Logger log = new Logger(UserDetailsService.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
	/*	ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		String code = (String)WebUtils.getSessionAttribute(sra.getRequest(), Constants.SESSION_KEY_VERIFY_CODE);
		WebUtils.setSessionAttribute(sra.getRequest(), Constants.SESSION_KEY_VERIFY_CODE, null);
		String verifycode = sra.getRequest().getParameter("verifycode");
		if (verifycode == null || !verifycode.equals(code)) {
			WebUtils.setSessionAttribute(sra.getRequest(), Constants.SESSION_KEY_ERROR_LOGIN, ServerUtil.getMessage("verifycode.incorrect"));
			throw new RuntimeMessageException("verifycode.incorrect");
		}
		*/
		log.debug("Invoke Method - loadUserByUsername");
		if (StringUtil.isNullOrEmpty(userName)) {
			//WebUtils.setSessionAttribute(sra.getRequest(), Constants.SESSION_KEY_ERROR_LOGIN, ServerUtil.getMessage("username.empty"));
			throw new RuntimeMessageException("object.not.found", ServerUtil.getMessage("user.label"));
		}
		
		User user = userService.findByName(userName);
		if (user == null ) {
			//WebUtils.setSessionAttribute(sra.getRequest(), Constants.SESSION_KEY_ERROR_LOGIN, ServerUtil.getMessage("username.notfound"));
			throw new RuntimeMessageException("object.not.found", ServerUtil.getMessage("user.label"));
		}
		log.debug("User found by name : " + userName);

		UserDetails userDetails  = new UserDetails(user);
		if (!userDetails.isEnabled()) {
			//WebUtils.setSessionAttribute(sra.getRequest(), Constants.SESSION_KEY_ERROR_LOGIN, ServerUtil.getMessage("user.inactive"));
			throw new RuntimeMessageException("object.not.found", ServerUtil.getMessage("user.label"));
		}
		return userDetails;
	}

}
