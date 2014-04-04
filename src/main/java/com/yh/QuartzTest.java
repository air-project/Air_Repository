package com.yh;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.yh.job.QuartzJob;


public class QuartzTest  {
		private static SchedulerFactory sf = new StdSchedulerFactory(); 
		private static String JOB_GROUP_NAME = "ddlib";
		private static String TRIGGER_GROUP_NAME = "ddlibTrigger";
	
	
	  /**添加一个定时任务，使用默认的任务组名，触发器名，触发器组名*/
	   public static void addJob(String jobName,Job job,String cronExpression)
	                               throws SchedulerException, ParseException{
		   addJob(jobName,null,jobName,null,job,cronExpression);
	   }
	  
	   /** 
	    * 添加一个定时任务
	    * @param jobName 任务名
	    * @param jobGroupName 任务组名
	    * @param triggerName 触发器名
	    * @param triggerGroupName 触发器组名
	    * @param job     任务
	    * @param cronExpression    时间设置，参考quartz说明文档
	    */
	   public static void addJob(String jobName,String jobGroupName,
	                             String triggerName,String triggerGroupName,Job job,String cronExpression)
	                               throws SchedulerException, ParseException{
		   if(StringUtils.isBlank(jobGroupName)){
			   jobGroupName = JOB_GROUP_NAME;
		   }
		   if(StringUtils.isBlank(triggerGroupName)){
			   triggerGroupName = TRIGGER_GROUP_NAME;
		   }
		   Scheduler sched = sf.getScheduler();
	       JobDetail jobDetail = new JobDetail(jobName, jobGroupName, job.getClass());//任务名，任务组，任务执行类
	       CronTrigger  trigger = new CronTrigger(jobName,triggerGroupName,cronExpression);//触发器名,触发器组,cron表达式
	       sched.scheduleJob(jobDetail,trigger);
	       //启动
	       if(!sched.isShutdown()){
	    	   sched.start();
	       }
	   }
	  
	   /** 
	    * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	    */
	   public static void modifyJobTime(String jobName,String cronExpression)
	                                  throws SchedulerException, ParseException{
		   modifyJobTime(jobName, null, cronExpression);
	   }
	  
	   /**
	    * 修改一个任务的触发时间
	    */
	   public static void modifyJobTime(String triggerName,String triggerGroupName,
	                                    String cronExpression)throws SchedulerException, ParseException{
		   if(StringUtils.isBlank(triggerGroupName)){
			   triggerGroupName = TRIGGER_GROUP_NAME;
		   }
	       Scheduler sched = sf.getScheduler();
	       Trigger trigger = sched.getTrigger(triggerName,triggerGroupName);
	       if(trigger != null){
	           CronTrigger ct = (CronTrigger)trigger;
	           //修改时间
	           ct.setCronExpression(cronExpression);
	           //重启触发器
	           sched.resumeTrigger(triggerName,triggerGroupName);
	       }
	   }
	  
	   /**移除一个任务和触发器(使用默认的任务组名，触发器名，触发器组名)*/
	   public static void removeJob(String jobName,String triggerName)
	                               throws SchedulerException{
		   removeJob(jobName, null, triggerName, null);
	   }
	  
	   /**移除一个任务和触发器 */
	   public static void removeJob(String jobName,String jobGroupName,
	                                String triggerName,String triggerGroupName)
	                               throws SchedulerException{
		   if(StringUtils.isBlank(jobGroupName)){
			   jobGroupName = JOB_GROUP_NAME;
		   }
		   if(StringUtils.isBlank(triggerGroupName)){
			   triggerGroupName = TRIGGER_GROUP_NAME;
		   }
	       Scheduler sched = sf.getScheduler();
	       sched.pauseTrigger(triggerName,triggerGroupName);//停止触发器
	       sched.unscheduleJob(triggerName,triggerGroupName);//移除触发器
	       sched.deleteJob(jobName,jobGroupName);//删除任务
	   }
	
	
	
	public static void main(String[] args) throws SchedulerException, ParseException {
		addJob("test", new QuartzJob(), "*/5 * * * * ?");
		addJob("zht", new QuartzJob(), "*/10 * * * * ?");
//		removeJob("myJob","myJobGroup", "myTrigger","myTriggerGroup");
//		removeJob("test","test");
//		removeJob("zht","zht");
		
	}
} 
