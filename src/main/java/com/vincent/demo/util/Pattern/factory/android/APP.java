package com.vincent.demo.util.Pattern.factory.android;

import com.vincent.demo.util.Pattern.factory.Config;

/**
 * 练习工厂模式
 * @author Administrator
 *
 */
public class APP {

	public static void main(String[] args) {
//		IAndroid iAndroid = new HightAndroid();
//		iAndroid.call();
		
//		IAndroid iAndroid = new MidAndroid();
//		iAndroid.call();
		
		IAndroid  iAndroid = new AnroidFacoty().getAndroid(Config.ANDROID_HIGHT);
		iAndroid.call();
	}
	
}
