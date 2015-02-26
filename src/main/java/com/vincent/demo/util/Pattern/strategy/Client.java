package com.vincent.demo.util.Pattern.strategy;

public class Client {

	public static void  main(String[] args){
		Sort sort = new BubbleSort();
		DataProcessing dp = new DataProcessing(sort);
		dp.doDataProcessing();
	}
}
