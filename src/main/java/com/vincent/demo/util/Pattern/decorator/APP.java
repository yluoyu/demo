package com.vincent.demo.util.Pattern.decorator;

/**
 * 测试装饰模式
 * 装饰者模式：动态地将责任附加到对象上，若要扩展对象，装饰者模式提供了比继承更弹性的替代方案
          要点： 装饰者与被装饰者拥有共同的超类，继承的目的是继承类型，而不是行为

          装饰者包含一个超类的对象，这样，可以在被装饰者行为前或者行为后加上新的行为，甚至取代原有的行为
          装饰者会使程序中出现很多小类，增加使用难度

         使用场景：对象由主体+许多可选的部件或者功能构成，使用继承或者接口会产生很多类，且很难扩展。
 * 
 * @author Administrator
 *
 */
public class APP {
	
	
    public static void main(String[] args) {
    	Huburger humburger = new ChickenBurger();
    	System.out.println(humburger.getName()+"价格："+humburger.getPrice());
    	Lettuce lettuce = new Lettuce(humburger);
    	System.out.println(lettuce.getName()+"价格："+lettuce.getPrice());
    	Chilli chilli = new Chilli(lettuce);
    	System.out.println(chilli.getName()+"价格："+chilli.getPrice());
	}
}
