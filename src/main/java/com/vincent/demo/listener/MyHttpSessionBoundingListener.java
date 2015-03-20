package com.vincent.demo.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 *监听对象绑定到Session以及钝化
 * 
 * @author Administrator
 *
 */
public class MyHttpSessionBoundingListener implements
		HttpSessionBindingListener {

	@Override
	public void valueBound(HttpSessionBindingEvent event) {

	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {

	}

}
