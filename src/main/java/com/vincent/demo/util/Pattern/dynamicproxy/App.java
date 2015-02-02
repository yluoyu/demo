package com.vincent.demo.util.Pattern.dynamicproxy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	SqlSession sqlSession = new SqlSession();
    	IUserInfo iUserInfo = sqlSession.getMapper(IUserInfo.class);
    	UserInfo userInfo = iUserInfo.getUser();
    	System.out.println("id:"+userInfo.getId()+",name:"+userInfo.getUsername());
    }
}
