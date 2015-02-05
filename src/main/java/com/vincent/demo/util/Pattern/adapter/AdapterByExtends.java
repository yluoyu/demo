package com.vincent.demo.util.Pattern.adapter;

/**
 * 通过继承的方式实现适配器
 * 
 * @author vincent
 *
 */

public class AdapterByExtends extends TwoPlug implements ThreePlug{

	@Override
	public void powerWithTree() {
		this.powerWithTwo();
	}

	
}
