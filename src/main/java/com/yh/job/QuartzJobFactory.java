package com.yh.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJobFactory implements Job{    
  public void execute(JobExecutionContext context) throws JobExecutionException    {        
      System.out.println("任务成功运行");        
      ScheduleJob  scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");        
      System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");        
  } 
}
