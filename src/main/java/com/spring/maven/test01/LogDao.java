package com.spring.maven.test01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Component
public class LogDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insert(){
		jdbcTemplate.execute("insert into testtx values(1,'log')");
		
	}
}
