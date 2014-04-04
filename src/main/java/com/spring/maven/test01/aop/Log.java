package com.spring.maven.test01.aop;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;


public class Log {
	private static Logger logger = LoggerFactory.getLogger(Log.class);
	class bb{
		private int id=9;
		public int getId(){
			return 90;
		}
	}
	public static void main(String[] args) {
	Log l= new Log();
	}

}
