package com.example.demospringboot.cronjobscheduler.controller;

import com.example.demospringboot.cronjobscheduler.TaskDefinition;
import com.example.demospringboot.cronjobscheduler.service.TaskDefinitionBean;
import com.example.demospringboot.cronjobscheduler.service.TaskSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/schedule")
public class JobSchedulingController {
    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

    @PostMapping(path = "/taskdef", consumes = "application/json", produces = "application/json")
    public void scheduleATask(@RequestBody TaskDefinition taskDefinition) {
        taskDefinitionBean.setTaskDefinition(taskDefinition);
        taskSchedulingService.scheduleATask(String.valueOf(UUID.randomUUID()), taskDefinitionBean, taskDefinition.getCronExpression());
    }
}
