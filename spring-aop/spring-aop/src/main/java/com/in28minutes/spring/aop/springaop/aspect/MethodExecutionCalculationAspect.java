package com.in28minutes.spring.aop.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//AOP
//Configuration
@Aspect
@Configuration
public class MethodExecutionCalculationAspect {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	//What kind of method calls I would intercept
	@Around("com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.trackTimeAnnotation()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {		
		long startTime = System.currentTimeMillis();
		Object returnObj = joinPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		LOGGER.info("Time taken by {} .is {}", joinPoint, timeTaken);
		return returnObj;
	}
}
