package com.yh.init;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yh.test.BaseSpringTestCase;
public class SpringContextInitTest extends BaseSpringTestCase{

	private static Logger logger=LoggerFactory.getLogger(SpringContextInitTest.class);
	
	@Test
	public void springContextInit() throws Exception{
		logger.info("......");
		logger.error("......");
		Thread.sleep(100000);
	}
}
