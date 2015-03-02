package com.vincent.demo.util.Pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察对象父类
 * 
 * @author Administrator
 *
 */
public class Observable {

	private List<Observer> list  = new ArrayList<Observer>();
	
	
	 public void attach(Observer observer){
	        
	        list.add(observer);
	        System.out.println("Attached an observer");
	    }
	    /**
	     * 删除观察者对象
	     * @param observer    观察者对象
	     */
	    public void detach(Observer observer){
	        
	        list.remove(observer);
	    }
	    /**
	     * 通知所有注册的观察者对象
	     */
	    public void nodifyObservers(String newState){
	        
	        for(Observer observer : list){
	            observer.update(newState);
	        }
	    }
}
