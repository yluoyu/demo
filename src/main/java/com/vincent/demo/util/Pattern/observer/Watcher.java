package com.vincent.demo.util.Pattern.observer;

public class Watcher implements Observer {

	@Override
	public void update(String status) {
		System.out.println("Watcher :" + status);

	}

}
