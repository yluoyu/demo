package com.vincent.demo.util.Pattern.factory.abstractfactory;

public class Client {

	public static void main(String[] args) {
		ComputerEngineer ce = new ComputerEngineer();
		AbstractFactory af = new IntelFactoty();
		ce.makeComputer(af);
	}
}
