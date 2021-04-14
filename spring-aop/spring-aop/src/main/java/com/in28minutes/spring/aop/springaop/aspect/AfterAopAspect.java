package com.in28minutes.spring.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

//AOP
//Configuration
@Aspect
@Configuration
public class AfterAopAspect {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	//What kind of method calls I would intercept
	@AfterReturning(
			value="com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.dataLayerExecution()",
			returning="result"
			)
	public void afterReturning(JoinPoint joinPoint, Object result) {
		//Advice
		LOGGER.info("{} returned with value {}", joinPoint, result);
	}
	
	@After("com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.dataLayerExecution()")
	public void after(JoinPoint joinPoint) {
		//Advice
		LOGGER.info("after execution of {}", joinPoint);
	}
}
