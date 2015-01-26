package com.vincent.demo.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.vincent.common.StringUtil;
import com.vincent.common.log.Logger;
import com.vincent.demo.controller.vo.JsonResponse;
import com.vincent.demo.entity.Role;
import com.vincent.demo.entity.User;
import com.vincent.demo.service.UserService;
import com.vincent.demo.web.auth.Token;
import com.vincent.demo.web.auth.UserDetails;

public class ServerUtil {
	private static final Logger log = new Logger(ServerUtil.class);
	private static ApplicationContext context;
	private static ViewResolver viewResolver;

	public static void setContext(ApplicationContext context) {
		ServerUtil.context = context;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanname, Class<T> clz) {
		Object bean = null;
		if (context != null) {
			bean = context.getBean(beanname);
		}
		if (bean != null && clz.isInstance(bean)) {
			return (T) bean;
		} else {
			return null;
		}
	}

	public static <T> T getBean(Class<T> clz) {
		T bean = null;
		if (context != null) {
			bean = context.getBean(clz);
		}
		return bean;
	}
	
	public static <S, T> List<T> copyBeanList(Collection<S> beanLst, Class<T> target) throws Exception{
		List<T> newList = new ArrayList<T>();
		for(S bean : beanLst){
			newList.add(copyBean(bean, target));
		}
		
		return newList;
	}
	
	@SuppressWarnings("unchecked")
	public static <S,T> T copyBean(S source, Class<T> target) throws Exception {
		if(source == null) return null;
		
		if(target == null){
			target = (Class<T>)source.getClass();
		}
		T newBean = target.newInstance();
		if(newBean.getClass().isAssignableFrom(source.getClass())){
			for(PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(target)){
				if(BeanUtils.isSimpleProperty(pd.getPropertyType())){
					PropertyDescriptor srcPd = BeanUtils.getPropertyDescriptor(source.getClass(), pd.getName());
					if(srcPd != null){
						Method wm = pd.getWriteMethod();
						Method rm = srcPd.getReadMethod();
						
						if(rm!=null && wm!=null 
								&& wm.getParameterTypes().length==1 
								&& wm.getParameterTypes()[0].isAssignableFrom(rm.getReturnType())){
							try{
								Object value = rm.invoke(source);
								if(value == null) continue;
								wm.invoke(newBean, value);
							}catch(Exception e){
							}
						}
					}
				}else{
					try{
						pd.getWriteMethod().invoke(newBean, new Object[]{null});
					}catch(Exception e){
					}
				}
			}
		} else {
			BeanUtils.copyProperties(source, newBean);
		}
		
		return newBean;
	}
	
	public static void copyBean(Object source, Object target) {
		for(PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(target.getClass())){
			PropertyDescriptor srcPd = BeanUtils.getPropertyDescriptor(source.getClass(), pd.getName());
			if(srcPd != null){
				Method wm = pd.getWriteMethod();
				Method rm = srcPd.getReadMethod();
				
				if(rm!=null && wm!=null 
						&& wm.getParameterTypes().length==1 
						&& wm.getParameterTypes()[0].isAssignableFrom(rm.getReturnType())){
					try{
						Object value = rm.invoke(source);
						if(value == null) continue;
						wm.invoke(target, value);
					}catch(Exception e){
					}
				}
			}
		}
	}
	
	public static String getMessage(String code, Object... args){
		return context.getMessage(code, args, Locale.CHINESE);
	}
	
	public static JsonResponse<Object> buildSuccessResponse(Object object, String message) {
		JsonResponse<Object> ret = new JsonResponse<Object>();
		ret.setSuccess(Constants.AJAX_RESPONSE_STATUS_SUCCESS);
		ret.setData(object);
		ret.setMessage(message);
		return ret;
	}

	public static JsonResponse<Object> buildErrorResponse(Object object, String errorMsg) {
		JsonResponse<Object> ret = new JsonResponse<Object>();
		ret.setSuccess(Constants.AJAX_RESPONSE_STATUS_ERROR);
		ret.setData(object);
		ret.setMessage(errorMsg);
		return ret;
	}

	public static Token getUserToken(){
		Token token = null;
		try {
			Object o = SecurityContextHolder.getContext().getAuthentication();
			if (o instanceof Token) {
				token = (Token)o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return token;
	}

	public static String getUserId(){
		String userId = null;
		try {
			Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (o instanceof UserDetails) {
				userId =  ((UserDetails)o).getUserid();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userId;
	}

	public static String getUserName() {
		String userName = null;
		try {
			Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (o instanceof UserDetails) {
				userName = ((UserDetails)o).getUsername();
			}
		} catch (Exception e) {
		}		
		return userName;
	}
	
	public static String getUserType(){
		String type = null;
		try {
			Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (o instanceof UserDetails) {
				type = ((UserDetails)o).getType();
			}
		} catch (Exception e) {
		}		
		return type;
	}
	
/*	public static boolean hasRole(String roleId) {
		boolean has = false;
		if (!StringUtil.isNullOrEmpty(roleId)) {
			Set<Role> userRoles = ServerUtil.getBean(UserService.class).findUserRolesById(ServerUtil.getUserId());
			if (userRoles != null && !userRoles.isEmpty()) {
				for (Role userRole : userRoles) {
					if (userRole.getId().equals(roleId)) {
						has = true;
						break;
					}
				}
			}
		}
		return has;
	}*/
	
/*	public static String[] filterRoles(String[] roles) {
		if (roles == null) {
			return new String[0];
		}		
		Set<Role> userRoles = ServerUtil.getBean(UserService.class).findUserRolesById(ServerUtil.getUserId());
		if (userRoles == null || userRoles.isEmpty()) {
			return new String[0];
		}
		List<String> filteredRoles = new ArrayList<String>();
		for (String role : roles) {
			for (Role userRole : userRoles) {
				if (userRole.getName().equals(role)) {
					filteredRoles.add(role);
					break;
				}
			}
		}
		return filteredRoles.toArray(new String[filteredRoles.size()]);
	}*/
	
	public static User getUser() {
		User user = null;
		try {
			String userId = getUserId();
			if (userId != null) {
				UserService us = ServerUtil.getBean(UserService.class);
				//user = us.(userId);
			}
		} catch (Exception e) {
		}		
		return user;
	}
	
	public static String getFirstError(BindingResult bindingResult){
		String error = null;
		if (bindingResult.hasErrors()) {
			error = bindingResult.getAllErrors().get(0).getDefaultMessage();
		}		
		return error;
		
	}
	
	public static String getJspPage(String viewName, Map<String, ?> model, HttpServletRequest request, HttpServletResponse resp) {
		String page = null;
		try {
			MockHttpServletResponse response = new MockHttpServletResponse();
			response.setCharacterEncoding(resp.getCharacterEncoding());
			getViewResolver(request).resolveViewName(viewName, Locale.getDefault()).render(model, request, response);
			page = response.getContentAsString().replaceAll("(^\\s+|\\s+$)", "");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return page;
	}
	
	public static ViewResolver getViewResolver(HttpServletRequest req) {
		synchronized (ServerUtil.class) {
			if (viewResolver == null) {
				viewResolver = (ViewResolver) RequestContextUtils.getWebApplicationContext(req).getBean("viewResolver");
			}
		}
		return viewResolver;
	}
	
	public static String getIPFromRequest(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("Proxy-Client-IP");
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getHeader("WL-Proxy-Client-IP");
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        	ip = request.getRemoteAddr();    
        } 
        return ip;
	}
	



	public static boolean isAjaxRequest(HttpServletRequest req){
		return "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
	}
	

}
