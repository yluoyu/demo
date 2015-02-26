package com.vincent.demo.util.Pattern.singleton;

import java.lang.reflect.Constructor;


/**
 * 饿汉式单例模式
 * 
 * @author Administrator
 *
 */
public class SingletonEH {
	
	private SingletonEH(){
		System.out.println("初始化");
	}
	
	public SingletonEH(int i){
		System.out.println("初始化方法2");
	}
	
	private static SingletonEH single = new SingletonEH();

	public static SingletonEH getInstance(){
		return single;
	}
	
	public void test(){
		System.out.println("处理过程");
	}
	
	public static void main(String[] args) {
		SingletonEH single = SingletonEH.getInstance();
		single.test();
		
		try {
			Class  clazz = Class.forName("com.vincent.demo.util.Pattern.singleton.SingletonEH");
			Constructor constructor = clazz.getConstructor(int.class);
			constructor.newInstance(2);
			
			//通过反射的方法可以调用
			clazz.getDeclaredConstructor().newInstance();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
