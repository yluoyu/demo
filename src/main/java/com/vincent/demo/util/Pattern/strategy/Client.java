package com.vincent.demo.util.Pattern.strategy;

public class Client {

	public static void  main(String[] args){
		Sort sort = null;
		DataProcessing dp = new DataProcessing(sort);
		dp.doDataProcessing();
	}
}
