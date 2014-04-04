package com.yh.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yh.init.SpringContextInitTest;

public class QuartzJob implements Job
{
	private static Logger logger=LoggerFactory.getLogger(QuartzJob.class);
	
    public void work()
    {
    	logger.info("Quartz的任务调度！！！");
    }

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("Quartz1.8的任务调度！！！");
	}
}
