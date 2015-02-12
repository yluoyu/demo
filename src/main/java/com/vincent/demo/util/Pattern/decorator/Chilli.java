package com.vincent.demo.util.Pattern.decorator;

/**
 * 辣椒
 * 
 * @author Administrator
 *
 */
public class Chilli extends Condiment {

	Huburger humburger;
	
	public Chilli( Huburger humburger){
		this.humburger = humburger;
	}
	@Override
	public String getName() {
		
		return this.humburger.getName() + " 加辣椒";
	}

	@Override
	public double getPrice() {
		return this.humburger.getPrice();//辣椒免费
	}

}
