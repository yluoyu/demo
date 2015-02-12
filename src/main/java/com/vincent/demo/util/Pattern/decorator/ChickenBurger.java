package com.vincent.demo.util.Pattern.decorator;

public class ChickenBurger extends Huburger {

	 public ChickenBurger() {
		 this.name = "鸡腿堡";
	}
	
	@Override
	public double getPrice() {
		return 10;
	}

}
