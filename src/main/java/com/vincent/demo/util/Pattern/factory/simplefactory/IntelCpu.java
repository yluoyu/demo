package com.vincent.demo.util.Pattern.factory.simplefactory;

public class IntelCpu implements Cpu {
	/**
	 * CPU的针脚
	 */
	
	private int pins = 0;
	
	public IntelCpu(int pins){
		this.pins = pins;
	}

	@Override
	public void calculate() {

		System.out.println("Intel的CPU针脚为：" + this.pins);
	}

}
