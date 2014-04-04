package com.spring.maven.test01.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopTest{

	private static Logger logger = LoggerFactory.getLogger(AopTest.class);
	@Pointcut("execution(* com..*(..))")
	public void pointCut(){
		
	}
	@Before("pointCut()")
	public void beforePoint(){
		logger.info("before Point");
	}
	@After("pointCut()")
	public void afterPoint(){
		logger.info("afterPoint");
	}
	
}
