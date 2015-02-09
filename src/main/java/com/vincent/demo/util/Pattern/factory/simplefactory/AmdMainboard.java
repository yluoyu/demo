package com.vincent.demo.util.Pattern.factory.simplefactory;

public class AmdMainboard implements Mainboard {

	private int cpuHoles = 0 ;
	
	public AmdMainboard(int cpuHoles) {
		this.cpuHoles = cpuHoles;
	}
	
	@Override
	public void installCpu() {

		System.out.println("Amd主板cpu插槽空数：" + this.cpuHoles);
	}

}
