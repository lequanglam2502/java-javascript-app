package com.colin.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectTest {
	private static boolean runAround = true;

	public static void main(String[] args) {
		new AspectTest().hello();
		//runAround = false;
		//new AspectTest().hello();
	}

	public void hello() {
		System.err.println("in hello");
	}

	@After("execution(void AspectTest.hello())")
	public void afterHello(JoinPoint joinPoint) {
		System.err.println("after " + joinPoint);
	}

	@Around("execution(void aspects.AspectTest.hello())")
	public void aroundHello(ProceedingJoinPoint joinPoint) throws Throwable {
		System.err.println("in around before " + joinPoint);
		if (runAround) {
			joinPoint.proceed();
		}
		System.err.println("in around after " + joinPoint);
	}

	@Before("execution(void aspects.AspectTest.hello())")
	public void beforeHello(JoinPoint joinPoint) {
		System.err.println("before " + joinPoint);
	}
	
	/*
	1 in around before execution(void aspects.TestAspect.hello())
	2 before execution(void aspects.TestAspect.hello())
	3 in hello
	4 after execution(void aspects.TestAspect.hello())
	5 in around after execution(void aspects.TestAspect.hello())
	6 in around before execution(void aspects.TestAspect.hello())
	7 in around after execution(void aspects.TestAspect.hello())
	*/
}
