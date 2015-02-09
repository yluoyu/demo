package com.vincent.demo.util.Pattern.factory.simplefactory;

public class AmdCpu implements Cpu {
	
	/**
	 * 针脚数
	 */
	int pins = 0;
	
	public AmdCpu(int pins){
		this.pins = pins;
	}

	@Override
	public void calculate() {

		System.out.println("Amd的Cpu针脚数：" + this.pins);
	}

}
