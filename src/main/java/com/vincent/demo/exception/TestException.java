package com.vincent.demo.exception;

import java.util.HashMap;
import java.util.Map;

public class TestException {  
    public TestException() {  
    }  
  
    public void TestException(){
    	System.out.println("OO");
    }
    
    boolean testEx() throws Exception {  
        boolean ret = true;  
        try {  
            ret = testEx1();  
        } catch (Exception e) {  
            System.out.println("testEx, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx, finally; return value=" + ret);  
            return ret;  
        }  
    }  
  
    boolean testEx1() throws Exception {  
        boolean ret = true;  
        try {  
            ret = testEx2();  
            if (!ret) {  
                return false;  
            }  
            System.out.println("testEx1, at the end of try");  
            return ret;  
        } catch (Exception e) {  
            System.out.println("testEx1, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx1, finally; return value=" + ret);  
            return ret;  
        } 
        
    }  
  
    boolean testEx2() throws Exception {  
        boolean ret = true;  
        try {  
            int b = 12;  
            int c;  
            for (int i = 2; i >= -2; i--) {  
                c = b / i;  
                System.out.println("i=" + i);  
            }  
            return true;  
        } catch (Exception e) {  
            System.out.println("testEx2, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx2, finally; return value=" + ret);  
            return ret;  
        }  
    }  
  
    
    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("KEY", "INIT");
         
        try {
            map.put("KEY", "TRY");
            return map;
        }
        catch (Exception e) {
            map.put("KEY", "CATCH");
        }
        finally {
            map.put("KEY", "FINALLY");
        }
         
        return map;
    }
    
    public static void main(String[] args) {  
        TestException testException1 = new TestException();  
        try {  
           // testException1.testEx();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        System.out.println(getMap().get("KEY").toString());
        
        String str1 = "hello";
        String str2 = "he" + new String("llo");
        System.err.println(str1 == str2);
        return ;
    }  
}  