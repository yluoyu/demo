package com.vincent.demo.util.Pattern.decorator;

/**
 * 汉堡基类
 * 
 * @author Administrator
 *
 */
public abstract class Huburger {
	
	protected  String name ; 
	
    public String getName(){  
	        return name;  
	    }  
	      
	public abstract double getPrice(); 

}
