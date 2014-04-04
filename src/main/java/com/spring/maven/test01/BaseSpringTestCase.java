/**
 * 
 */
package com.spring.maven.test01;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration({
	"/applicationContext.xml"
})
public class BaseSpringTestCase extends
AbstractJUnit4SpringContextTests {
}
