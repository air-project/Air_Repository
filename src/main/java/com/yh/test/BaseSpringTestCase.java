/**
 * 
 */
package com.yh.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration({
	"/applicationContext.xml"
})
public class BaseSpringTestCase extends
		//AbstractTransactionalJUnit4SpringContextTests
			AbstractJUnit4SpringContextTests
		{
//	private static Logger logger=LoggerFactory.getLogger(BaseSpringTestCase.class);
//	public BaseSpringTestCase(){
//		logger.info("Spring Context Init....");
//	}
}
