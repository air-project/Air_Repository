package com.spring.maven.test01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


	public class TestWebContext {
	    ApplicationContext context;
	    @Before
	    public void loadSpringContext(){
	        context = new ClassPathXmlApplicationContext("applicationContext.xml");
	    }
	    
	    @Test
	    public void test() {
	    System.out.println(context.getBean("AA"));
	    }




}
