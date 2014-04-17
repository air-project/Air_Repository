package com.yh.init;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.yh.test.BaseSpringTestCase;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MyTest extends BaseSpringTestCase{

	public static Logger logger=LoggerFactory.getLogger(MyTest.class);
	
	
	private static Configuration freemarkerConfiguration;

	@Test
	public void aa (){
		try {
			freemarkerConfiguration = new Configuration();
			freemarkerConfiguration.setObjectWrapper(new DefaultObjectWrapper());
			
			Properties  props = new Properties();
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("freemarker.properties"));
			freemarkerConfiguration.setSettings(props);
			// 查找并加载freemarker模板文件.
			freemarkerConfiguration.setClassForTemplateLoading(MyTest.class,"../");
			Template template =freemarkerConfiguration.getTemplate("name.ftl");
			Map model=new HashMap();
			model.put("name", new Date());
			logger.info("freemaker Test Content:"+FreeMarkerTemplateUtils.processTemplateIntoString(template,
					model));
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
