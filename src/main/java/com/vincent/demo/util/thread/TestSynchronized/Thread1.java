package com.vincent.demo.util.thread.TestSynchronized;

public class Thread1 extends Thread{
	private App app;
	
	public Thread1(App app){
		this.app = app;
	}
	
	@Override
	public void run(){
		this.app.increase();
	}

}
