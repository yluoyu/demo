package com.vincent.demo.util.Pattern.observer;

public class Cient {

	public static void main(String[] args) {
		 Watched watched = new Watched();
		 Watcher watcher = new Watcher();
		 watched.attach(watcher);
		 watched.setData("test!");

	}

}
