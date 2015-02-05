package com.vincent.demo.util.IO;

import java.io.File;
import java.net.URL;

/**
 * 测试不同地方获取路径
 * 
 * @author Administrator
 *
 */
public class PathUtil {
	
	public static void main(String[] args) {
		
		//1、利用System.getProperty()获得
		System.out.println("System.getProperty():" + System.getProperty("user.dir"));
		
		//2、利用FILE类获取
		File directory = new File("");
		try{ 
		    System.out.println("file.getCanonicalPath()" + directory.getCanonicalPath());//获取标准的路径 
		    System.out.println("file.getAbsolutePath()" + directory.getAbsolutePath());//获取绝对路径 
		}catch(Exception e){
			
		} 
		//3、利用类获取
		
		 System.out.println("Class:" + new PathUtil().getClass().getResource("/").getPath());
		 
		 System.out.println("java.class.path:" + System.getProperty("java.class.path"));
		 
		 URL xmlpath = new PathUtil().getClass().getClassLoader().getResource("test.txt"); 
		 System.out.println("getClassLoader():" + xmlpath);
	}

}
