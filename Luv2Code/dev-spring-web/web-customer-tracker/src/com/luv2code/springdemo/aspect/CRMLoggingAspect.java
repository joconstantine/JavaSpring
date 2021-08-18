package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// set up logger
	private final Logger myLogger = Logger.getLogger(getClass().getName());

	// set up pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {
	}

	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {

		// display method we are calling
		String theMethod = theJoinPoint.getStaticPart().toString();
		myLogger.info("=====>> in @Before: calling method: " + theMethod);

		// display the arguments to the method

		// get the arguments
		Object[] args = theJoinPoint.getArgs();

		// loop through and display args
		for (Object tempArg : args) {
			myLogger.info("=====>> argument: " + tempArg);
		}

	}

	// add @AfterReturning advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

		// display method we are returning from
		String theMethod = theJoinPoint.getStaticPart().toString();
		myLogger.info("=====>> in @After: from method: " + theMethod);

		// display returned data
		myLogger.info("=====>> result: " + theResult);

	}

}
