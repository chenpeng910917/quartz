package com.quartz.quartz.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CtripScenicJob implements Job{

    private Logger logger = LoggerFactory.getLogger(CtripScenicJob.class);

    @Autowired
    private CtripScenicTask ctripScenicTask;

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        logger.info("JobName: {}", context.getJobDetail().getKey().getName());
        ctripScenicTask.loadComment();
    }



}