package com.example.demospringboot.cronjobscheduler.interfaces;

import com.example.demospringboot.cronjobscheduler.ScheduleTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTimeConfigRepository extends JpaRepository<ScheduleTimeEntity, Long> {
    ScheduleTimeEntity findByValue(String value);
}
