package com.quartz.quartz.service;

import java.util.List;

import com.quartz.quartz.model.TaskInfo;
import org.quartz.SchedulerException;

public interface TaskService {

    List<TaskInfo> list();

    void addJob(TaskInfo info);

    void edit(TaskInfo info);

    void delete(String jobName, String jobGroup);

    void pause(String jobName, String jobGroup);

    void resume(String jobName, String jobGroup);

    boolean checkExists(String jobName, String jobGroup)throws SchedulerException;
}