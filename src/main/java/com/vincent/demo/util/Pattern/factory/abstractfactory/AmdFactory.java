package com.vincent.demo.util.Pattern.factory.abstractfactory;

import com.vincent.demo.util.Pattern.factory.simplefactory.AmdCpu;
import com.vincent.demo.util.Pattern.factory.simplefactory.AmdMainboard;
import com.vincent.demo.util.Pattern.factory.simplefactory.Cpu;
import com.vincent.demo.util.Pattern.factory.simplefactory.Mainboard;

public class AmdFactory implements AbstractFactory {

	@Override
	public Cpu createCpu() {
		
		return new AmdCpu(985);
	}

	@Override
	public Mainboard createMainboard() {
		
		return new AmdMainboard(985);
	}

}
