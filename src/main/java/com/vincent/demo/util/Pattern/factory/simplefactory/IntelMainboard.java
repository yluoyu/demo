package com.vincent.demo.util.Pattern.factory.simplefactory;

public class IntelMainboard implements Mainboard {
    
	/**
	 * CPU插槽空数
	 */
	private int cpuHoles = 0;
	
	public IntelMainboard( int cpuHoles){
		this.cpuHoles = cpuHoles;
	}
	
	@Override
	public void installCpu() {
		
        System.out.println("Intel主板cpu插槽控数：" + this.cpuHoles);
	}

}
