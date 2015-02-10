package com.vincent.demo.util.Pattern.factory.abstractfactory;

import com.vincent.demo.util.Pattern.factory.simplefactory.Cpu;
import com.vincent.demo.util.Pattern.factory.simplefactory.CpuFactory;
import com.vincent.demo.util.Pattern.factory.simplefactory.Mainboard;
import com.vincent.demo.util.Pattern.factory.simplefactory.MainboardFactory;

public class IntelFactoty implements AbstractFactory{

	@Override
	public Cpu createCpu() {
		Cpu cpu = CpuFactory.createCpu(1);
		return cpu;
	}

	@Override
	public Mainboard createMainboard() {
		
		Mainboard mainboard = MainboardFactory.createMainboard(1);
		return mainboard;
	}

}
