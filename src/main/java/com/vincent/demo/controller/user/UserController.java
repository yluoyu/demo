package com.vincent.demo.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.vincent.common.StringUtil;
import com.vincent.common.Util;
import com.vincent.demo.entity.User;
import com.vincent.demo.service.UserService;
import com.vincent.demo.service.impl.UserServiceImpl;
import com.vincent.demo.util.Constants;
import com.vincent.demo.util.ServerUtil;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	private static final String PREFIX = "user/";
	private static final String LOGIN_MESSAGE_KEY = UserController.class.getName()+".message";
	private static final String LOGIN_ERROR_MESSAGE_KEY = UserController.class.getName()+".errorMsg";
	
	@RequestMapping("/login")
	public String loginPage(HttpServletRequest request, HttpServletResponse resp) {
		User user = new User();
		user.setId("123456");
		user.setUsername("test001");
		try {
		//	userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PREFIX + "login";
	}
	@RequestMapping("/regedit")
	public String regeditPage(HttpServletRequest request, HttpServletResponse resp) {
		try {
		//	userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PREFIX + "regedit";
	}
	@RequestMapping("/doRegedit")
	public String doRegedit(HttpServletRequest request, HttpServletResponse resp,String username,String pwd) {
		try {
			User user = new User();
			user.setPassword(Util.getMD5Str(pwd));
			user.setUsername(username);
			userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PREFIX + "login";
	}
	@RequestMapping("/user")
	public String user(HttpServletRequest request, HttpServletResponse resp) {
		try {
			System.out.println("USER_TEST");
		//	userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PREFIX + "regedit";
	}
	
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request, HttpServletResponse resp) {
		try {
			System.out.println("ADMIN_TEST");
		//	userService.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PREFIX + "regedit";
	}
	
	@RequestMapping("/denied")
	public ModelAndView deniedPage(HttpServletRequest request) {
		WebUtils.setSessionAttribute(request, LOGIN_ERROR_MESSAGE_KEY, ServerUtil.getMessage("access.denied"));
		return new ModelAndView(new RedirectView("/user/login", true));
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutPage(HttpServletRequest request) {
		WebUtils.setSessionAttribute(request, LOGIN_ERROR_MESSAGE_KEY, ServerUtil.getMessage("logout.success"));
		return new ModelAndView(new RedirectView("/user/login", true));
	}
	
	@RequestMapping("/invalid")
	public ModelAndView sessionInvalidPage(HttpServletRequest request) {
		WebUtils.setSessionAttribute(request, LOGIN_ERROR_MESSAGE_KEY, ServerUtil.getMessage("session.invalid"));
		return new ModelAndView(new RedirectView("/user/login", true));
	}
	
	@RequestMapping("/expired")
	public ModelAndView sessionExpiredPage(HttpServletRequest request) {
		WebUtils.setSessionAttribute(request, LOGIN_ERROR_MESSAGE_KEY, ServerUtil.getMessage("session.expired"));
		return new ModelAndView(new RedirectView("/user/login", true));
	}
	
	@RequestMapping("/failed")
	public ModelAndView loginFailedPage(HttpServletRequest request) {
		String loginError = StringUtil.nullToEmpty(WebUtils.getSessionAttribute(request, Constants.SESSION_KEY_ERROR_LOGIN));
		if (StringUtil.isNullOrEmpty(loginError, false)) {
			WebUtils.setSessionAttribute(request, LOGIN_ERROR_MESSAGE_KEY, ServerUtil.getMessage("login.failed"));
		} else {
			WebUtils.setSessionAttribute(request, Constants.SESSION_KEY_ERROR_LOGIN, null);
			WebUtils.setSessionAttribute(request, LOGIN_ERROR_MESSAGE_KEY, loginError);
		}
		return new ModelAndView(new RedirectView("/user/login", true));
	}
}
