package com.spring.maven.test01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Component
public class TestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private LogDao logDao;
    @Transactional
	public void insert(){
//    	logDao.insert();
		jdbcTemplate.execute("insert into testtx values(1,'teste3')");
//		int i=9/0;
		
	}
}
