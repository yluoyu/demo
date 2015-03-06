package com.vincent.demo.util.thread.TestSynchronized;


public class App  {
 
	public  int count = 0;
	
	public  void increase(){
		 //这里延迟1毫秒，使得结果明显
        try {
        	Thread.sleep(3);
        } catch (InterruptedException e) {
        }
      //  count++;
        synchronized (this) {
        	count++;
		}
		
	}
	
	public void run(){
		increase();
	}
	
	
	public static void main(String[] args) {
		App app = new App();
		  //同时启动1000个线程，去进行i++计算，看看实际结果
    	Thread threads[]=new Thread[1000];
    	 for (int i = 0; i < 1000; i++) {
         	threads[i] = new Thread1(app);
         	threads[i].start();
         }
        
        for(int i=0;i<1000;i++){
        	try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
 
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + app.count);
	}
}
