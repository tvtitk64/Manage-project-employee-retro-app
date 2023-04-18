package com.example.demospringboot.cronjobscheduler.service;

import com.example.demospringboot.cronjobscheduler.ScheduleTimeEntity;
import com.example.demospringboot.cronjobscheduler.interfaces.ScheduleTimeConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTimeService {
    @Autowired
    private ScheduleTimeConfigRepository scheduleTimeConfigRepository;

    public ScheduleTimeEntity createCustomTime(ScheduleTimeEntity scheduleTimeEntity) {
        ScheduleTimeEntity entity = scheduleTimeConfigRepository.findByValue(scheduleTimeEntity.getValue());
        if (entity != null) {
            return entity;
        } else {
            return scheduleTimeConfigRepository.save(scheduleTimeEntity);
        }
    }
    public List<ScheduleTimeEntity> getAll() {
        return scheduleTimeConfigRepository.findAll();
    }

    public ScheduleTimeEntity findByValue(ScheduleTimeEntity scheduleTimeEntity) {
        return scheduleTimeConfigRepository.findByValue(scheduleTimeEntity.getValue());
    }
}
