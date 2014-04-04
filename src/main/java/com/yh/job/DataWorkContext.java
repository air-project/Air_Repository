package com.yh.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataWorkContext {
	private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();

	static {
		for (int i = 0; i < 5; i++) {
			ScheduleJob job = new ScheduleJob();
			job.setJobId("10001" + i);
			job.setJobName("data_import" + i);
			job.setJobGroup("dataWork");
			job.setJobStatus("1");
			job.setCronExpression("0/5 * * * * ?");
			job.setDesc("数据导入任务");
			addJob(job);
		}
	}

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 */
	public static void addJob(ScheduleJob scheduleJob) {
		jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
	}

	public static ScheduleJob getJob(String jobId) {
		return jobMap.get(jobId);
	}

	public static List getAllJob() {
		List jobList = new ArrayList(jobMap.size());
		for (Map.Entry entry : jobMap.entrySet()) {
			jobList.add(entry.getValue());
		}
		return jobList;
	}
}
