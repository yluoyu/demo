package com.vincent.demo.web.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vincent.demo.util.ServerUtil;


public class ContextListener implements ServletContextListener {


	public void contextInitialized(ServletContextEvent sce) {
		ServerUtil.setContext(WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext()));
	}
	
	
	public void contextDestroyed(ServletContextEvent sce) {
		
	}	

}
