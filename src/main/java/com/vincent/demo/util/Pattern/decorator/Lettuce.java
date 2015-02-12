package com.vincent.demo.util.Pattern.decorator;
/**
 * 生菜
 * 
 * @author Administrator
 *
 */
public class Lettuce extends Condiment {

	Huburger humburger;
	
	public Lettuce(Huburger humburger){
		this.humburger = humburger;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return humburger.getName() + " 加生菜";
	}

	@Override
	public double getPrice() {
		
		return humburger.getPrice()+1.5;
	}



}
