package com.example.demospringboot.cronjobscheduler.service;

import com.example.demospringboot.cronjobscheduler.TaskDefinition;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class TaskDefinitionBean implements Runnable{
    private TaskDefinition taskDefinition;

    @Override
    public void run() {
        System.out.println("Action: " + taskDefinition.getActionType());
        System.out.println("Data: " + taskDefinition.getData());
    }
}
