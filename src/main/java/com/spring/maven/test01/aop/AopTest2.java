package com.spring.maven.test01.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10)
public class AopTest2{

	private static Logger logger = LoggerFactory.getLogger(AopTest2.class);
	@Pointcut("execution(* com..*(..))")
	public void pointCut1(){
		
	}
	@Around("pointCut1()")
	public Object aroundMethod(ProceedingJoinPoint joinpoint) throws Throwable {
		Object result = null;
		try {
				logger.info("before Point");    
			 result = joinpoint.proceed();
			 logger.info("afterPoint");  
		} catch (Throwable e) {
			throw e;
		}
		return result;
	}
}
