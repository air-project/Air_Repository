package com.spring.maven.test01;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



	public class Test01 extends BaseSpringTestCase {
		@Resource
		AA a;
		@Autowired
		TestDao dao;
		@Test
	    public void test22() {
	    	System.out.println(a.getId());
	    }
		@Test
		public void testdao(){
			dao.insert();
		}
}
