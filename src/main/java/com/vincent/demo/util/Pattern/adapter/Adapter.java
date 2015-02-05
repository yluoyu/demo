package com.vincent.demo.util.Pattern.adapter;

/**
 * 通过组合的方式实现适配器
 * 
 * @author vincent
 *
 */

public class Adapter implements ThreePlug{

	private TwoPlug plug;
	
	public Adapter(TwoPlug pulg){
		this.plug = pulg;
	}

	@Override
	public void powerWithTree() {
		plug.powerWithTwo();
		
	}
}
