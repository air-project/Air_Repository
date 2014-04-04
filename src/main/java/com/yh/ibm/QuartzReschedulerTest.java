package com.yh.ibm;

import org.quartz.Scheduler; 
import org.quartz.SchedulerException; 
import org.quartz.SchedulerFactory; 
import org.quartz.Trigger; 
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory; 

public class QuartzReschedulerTest { 
public static void main(String[] args) throws SchedulerException { 
// 初始化一个 Schedule Factory 
SchedulerFactory schedulerFactory = new StdSchedulerFactory(); 
// 从 schedule factory 中获取 scheduler 
Scheduler scheduler = schedulerFactory.getScheduler(); 
TriggerKey triggerKey=TriggerKey.triggerKey("myTrigger", "myTriggerGroup"); 

// 从 schedule factory 中获取 trigger 

Trigger trigger = scheduler.getTrigger(triggerKey); 
// 重新开启调度任务
scheduler.rescheduleJob(triggerKey, trigger); 
scheduler.start(); 
} 
}
