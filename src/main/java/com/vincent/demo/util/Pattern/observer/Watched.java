package com.vincent.demo.util.Pattern.observer;

public class Watched extends Observable {

	private String data = "";

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
		this.nodifyObservers(data);
	}
}
