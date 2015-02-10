package com.vincent.demo.util.Pattern.factory.simplefactory;



public class CpuFactory {

	/**
	 * 
	 * @param type
	 * @return
	 */
	public static Cpu createCpu(int type){
        Cpu cpu = null;
        if(type == 1){
            cpu = new IntelCpu(755);
        }else if(type == 2){
            cpu = new AmdCpu(938);
        }
        return cpu;
    }
}
