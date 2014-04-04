package com.yh.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzHelloJob implements Job {

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(format.format(new Date()) + " Hello Quartz!");
    }
}
