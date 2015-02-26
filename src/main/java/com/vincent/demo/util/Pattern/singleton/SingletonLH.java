package com.vincent.demo.util.Pattern.singleton;

/**
 * 懒汉单例模式
 * 
 * @author Administrator
 *
 */
public class SingletonLH {

	private SingletonLH(){
		System.out.println("初 始 化");
	}
	
	private static SingletonLH single = null;
	
	// 多线程时应加同步
	public synchronized static SingletonLH getInstance(){
		if(single == null){
			single = new SingletonLH(); 
		}
		return single;
	}
	
}
