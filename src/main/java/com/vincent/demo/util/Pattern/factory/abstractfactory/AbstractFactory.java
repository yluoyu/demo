package com.vincent.demo.util.Pattern.factory.abstractfactory;

import com.vincent.demo.util.Pattern.factory.simplefactory.Cpu;
import com.vincent.demo.util.Pattern.factory.simplefactory.Mainboard;

/**
 * 定义接口
 * @author Administrator
 *
 */
public interface AbstractFactory {

	/**
	 * 创建CPU
	 * @return
	 */
	public Cpu createCpu();
	
	/**
	 * 创建主板
	 * @return
	 */
	public Mainboard createMainboard();
}
