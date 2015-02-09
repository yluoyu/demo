package com.vincent.demo.util.Pattern.factory.simplefactory;

/**
 * 电脑组装工程师类
 * 
 * @author Administrator
 *
 */
public class ComputerEngineer {

	/**
	 *装机需要的cpu
	 */
	private Cpu cpu = null;
	
	/**
	 * 装机需要的主板
	 */
	private Mainboard mainboard = null;
	
	public void makeComputer(int cpuType,int mainboard){
		
		/**
		 * 定义装机的步骤
		 */
		//1:首先准备好装机所需要的配件
        prepareHardwares(cpuType, mainboard);
        //2:组装机器
        //3:测试机器
        //4：交付客户
	}
	
	private void prepareHardwares(int cpuType,int mainboard){
		
	}
}
