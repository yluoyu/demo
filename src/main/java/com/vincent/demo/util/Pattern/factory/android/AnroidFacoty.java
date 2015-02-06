package com.vincent.demo.util.Pattern.factory.android;

public class AnroidFacoty {

	public IAndroid getAndroid(String type){
		
		IAndroid iAndroid = null;
		try {
			iAndroid = (IAndroid)Class.forName(type).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return iAndroid;
		
	}
}
