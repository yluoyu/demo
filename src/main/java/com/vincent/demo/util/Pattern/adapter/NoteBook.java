package com.vincent.demo.util.Pattern.adapter;


/**
 * 以笔记本充电，只有两相插座，和三相插头情景演示适配器模式
 * 
 * 
 * @author vincent
 *
 */
public class NoteBook {
	
	ThreePlug three;
	
	public NoteBook(ThreePlug three){
		this.three = three;
	}

	public void charge(){
		this.three.powerWithTree();
	}
	
	public static void main(String[] args) {
		//组合的方式实现
		TwoPlug two = new TwoPlug();
		Adapter adpter = new Adapter(two);
		NoteBook nb = new NoteBook(adpter);
		nb.charge();
		
		
		//继承的方式实现
		
		AdapterByExtends adapterByExtends = new AdapterByExtends();
		nb = new NoteBook(adapterByExtends);
		nb.charge();
		
	}
}
