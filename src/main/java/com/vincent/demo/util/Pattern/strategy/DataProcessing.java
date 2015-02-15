package com.vincent.demo.util.Pattern.strategy;

/**
 *  数据处理类，代表Context，上下文环境
 * 
 * @author yangwc
 *
 */
public class DataProcessing {

	private Sort sort;
	
	public DataProcessing(Sort sort){
	   this.sort = sort;
	}
	
	public void doDataProcessing(){
		System.out.println("数据收集");
		this.sort.doSort();
		System.out.println("数据处理");
	}
}
