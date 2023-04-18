package com.example.demospringboot.cronjobscheduler.controller;

import com.example.demospringboot.cronjobscheduler.ScheduleTimeEntity;
import com.example.demospringboot.cronjobscheduler.SchedulingConfig;
import com.example.demospringboot.cronjobscheduler.service.ScheduleTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j

public class ScheduleConfigController {
    @Autowired
    private ScheduleTimeService scheduleTimeService;

    @Autowired
    private SchedulingConfig schedulingConfig;

    @PostMapping("/schedule/create")
    public ResponseEntity createNewScheduleTime(@RequestBody ScheduleTimeEntity value) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleTimeService.createCustomTime(value));
    }

    @GetMapping("/refreshcronschedule")
    public void refreshCronSchedule() {
        schedulingConfig.refreshCronSchedule();
    }
}
