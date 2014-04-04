package com.spring.maven.test01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class AA {
	private static Logger logger = LoggerFactory.getLogger(AA.class);
	public int id=2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void p(){
		logger.error("SSSS");
		logger.warn("warn");
		logger.info("aainfo");
	}
	
}
