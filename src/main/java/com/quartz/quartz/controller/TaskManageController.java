package com.quartz.quartz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quartz.quartz.model.TaskInfo;
import com.quartz.quartz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;


/**
 * 任务管理
 */
@Controller
@RequestMapping("/qy/api/task/")
public class TaskManageController {

    @Autowired(required=false)
    private TaskService taskService;

    /**
     * Index.jsp
     */
    @RequestMapping(value={"", "/", "index"})
    public String info(){
        return "index.jsp";
    }

    /**
     * 任务列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="list")
    public String list(){
        Map<String, Object> map = new HashMap<>();
        List<TaskInfo> infos = taskService.list();
        map.put("rows", infos);
        map.put("total", infos.size());
        return JSON.toJSONString(map);
    }

    /**
     * 保存定时任务
     * @param info
     * http://localhost:21000/qy/api/task/save?jobName=com.quartz.quartz.quartz.CtripScenicJob&jobGroup=group3&jobDescription=job描述&cronExpression=0 13 9 ? * *
     * jobName  是要执行的类（包名+类名）   jobGroup是组 每次执行的组名不能一样  jobDescription 是描述  cronExpression是定时时间
     */
    @ResponseBody
    @RequestMapping(value="save", produces = "application/json; charset=UTF-8")
    public String save(TaskInfo info){
        try {
            if(info.getId() == 0) {
                taskService.addJob(info);
            }else{
                taskService.edit(info);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "成功";
    }

    /**
     * 删除定时任务
     * @param jobName
     * @param jobGroup
     */
    @ResponseBody
    @RequestMapping(value="delete/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
    public String delete(@PathVariable String jobName, @PathVariable String jobGroup){
        try {
            taskService.delete(jobName, jobGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "成功";
    }

    /**
     * 暂停定时任务
     * @param jobName
     * @param jobGroup
     */
    @ResponseBody
    @RequestMapping(value="pause/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
    public String pause(@PathVariable String jobName, @PathVariable String jobGroup){
        try {
            taskService.pause(jobName, jobGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "成功";
    }

    /**
     * 重新开始定时任务
     * @param jobName
     * @param jobGroup
     */
    @ResponseBody
    @RequestMapping(value="resume/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
    public String resume(@PathVariable String jobName, @PathVariable String jobGroup){
        try {
            taskService.resume(jobName, jobGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "成功";
    }
}
