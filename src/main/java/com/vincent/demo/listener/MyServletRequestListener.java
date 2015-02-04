package com.vincent.demo.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {

		System.out.println("ServletRequest : Destroyed");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {

		System.out.println("ServletRequest : Initialized");
	}

}
