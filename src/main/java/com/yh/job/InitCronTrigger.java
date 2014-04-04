package com.yh.job;


import java.io.Serializable;  
import java.text.ParseException;  
  
import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

@SuppressWarnings("serial")
public class InitCronTrigger extends CronTriggerFactoryBean implements Serializable {    
    
	
	private Scheduler scheduler;
	
    public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public InitCronTrigger() throws ParseException {  
        setCronExpression(getCronExceptionDB());  //在构造方法中设置cronException参数  
    }
    
	private String getCronExceptionDB(){ 
//		scheduler.get
		String jiange = "0/1 * * * * ?";
        String sql = "select CRON from t_test_task_trigger where available = 1 and trigger_name = 'cronTrigger'";    
        System.out.println("*****" + sql); 
       for(int i = 0 ;i<31;i++){
        	if(i == 10) jiange = "0/2 * * * * ?";
        	else if(i == 20) jiange = "0/3 * * * * ?";
        	else if(i == 30) jiange = "0/4 * * * * ?";
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        return jiange; //此处作为测试，直接返回结果，可以根据需要从数据库中读取  
//        return "* * * 11 12 ?"; //此处作为测试，直接返回结果，可以根据需要从数据库中读取  
    }
}