package com.yh.init;

import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.test.annotation.Repeat;

import com.yh.test.BaseSpringTestCase;




public class SpringContextInitTest extends BaseSpringTestCase{

	public static Logger logger=LoggerFactory.getLogger(SpringContextInitTest.class);
	
	@Test
//	@Ignore
	public void springContextInit() throws Exception{
		logger.info("springContextInit");
	}
	@Before
	public void before() throws Exception{
		logger.info("before=setup use init data");
	}
	@After
	public void after() throws Exception{
		logger.info("after=tearDown use clean or destory data");
	}
	
	@AfterClass
	public  static void afterClass() throws Exception{
		logger.info("after all method run,the method run 1 time");
	}
	@BeforeClass
	public  static void beforeClass() throws Exception{
		logger.info("before all method run,the method run 1 time");
	}
	@Test
	@Repeat(3)
	public void messageSource() throws Exception{
		logger.info("wwwww{0}国际化测试{1}");
		MessageSource ms=(MessageSource)applicationContext.getBean("messageSource");
		logger.info(ms.getMessage("global.policycontrol.reasonCode.errorMsg", new String[]{"a","b"}, Locale.US));
	}
	
}
