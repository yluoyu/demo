package com.vincent.demo.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

	@Pointcut("execution(* com.vincent.demo.service.impl.*.*(..))")  
    private void pointCutMethod() {  
    } 

	@Before("pointCutMethod()")
	public void doBefore() {
		System.out.println("模拟进行权限检查。");
	}
	
	@After("pointCutMethod()")
	public void doAfter() {
		System.out.println("模拟进行权限检查。");
	}
}
